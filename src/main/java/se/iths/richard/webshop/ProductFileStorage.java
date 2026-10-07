package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {
    private final static Path DEFAULT_FILEPATH = Path.of("src/main/resources/products.scsv");

    private final Path filePath;
    List<Product> productList;

    public ProductFileStorage() {
        this(DEFAULT_FILEPATH);
    }

    public ProductFileStorage(Path filePath) {
        this.filePath = filePath;

        // Should create the file and resources directory so in case it does not exist
        Path path = Path.of("src/main/resources");
        if (!Files.isDirectory(path) || !Files.exists(filePath)) {
            try {
                Files.createDirectories(path);
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // PRODUCTS.SCSV ALREADY EXISTS!
        productList = new ArrayList<>();
    }

    @Override
    public void saveProduct(Product product) {
        String productString = product.toFileLine();

        try {
            Files.writeString(filePath, productString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getProducts() {
        populateProductList();
        return productList;
    }

    private void populateProductList() {
        productList = new ArrayList<>();

        if (!Files.isReadable(filePath)) {
            throw new RuntimeException("CAN'T READ FILE SOMETHING IS SUPER WRONG!");
        }

        try {
            List<String> rows = Files.readAllLines(filePath);

            for (String row : rows) {
                Product product;

                try {
                    product = Product.fromFileLine(row);
                } catch (NumberFormatException e) {
                    IO.println(e.getMessage() + ": " + row);
                    continue;
                } catch (IllegalArgumentException e) {
                    IO.println(e.getMessage());
                    continue;
                }

                productList.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProduct(String articleNumber) {
        populateProductList();
        for (Product product : productList) {
            if (product.getArticleNumber().equals(articleNumber))
                return product;
        }

        return null;
    }
}
