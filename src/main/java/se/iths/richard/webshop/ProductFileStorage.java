package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Product;

import java.nio.file.Path;
import java.util.List;

public class ProductFileStorage implements ProductStorage {
    private Path file = Path.of("src/main/resources/products.ssv");

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(String articleNumber) {
        return null;
    }
}
