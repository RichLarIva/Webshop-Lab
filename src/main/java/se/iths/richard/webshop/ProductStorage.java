package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Product;

import java.util.List;

public interface ProductStorage {

    void saveProduct(Product product);

    List<Product> getProducts();

    Product getProduct(String articleNumber);
}
