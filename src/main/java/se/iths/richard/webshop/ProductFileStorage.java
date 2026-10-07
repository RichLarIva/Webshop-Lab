package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {
    private final static Path DEFAULT_FILEPATH = Path.of("src/main/resources/products.scsv");

    private final Path filePath;

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
        IO.println("FILE ALREADY EXISTS");
    }

    @Override
    public void saveProduct(Product product) {
        String productString = product.toFileLine();


    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();

        if (Files.isReadable(filePath)) {
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
        } else {
            throw new RuntimeException("CAN'T READ FILE SOMETHING IS SUPER WRONG!");
        }


        return productList;
    }

    @Override
    public Product getProduct(String articleNumber) {
        return null;
    }
}
