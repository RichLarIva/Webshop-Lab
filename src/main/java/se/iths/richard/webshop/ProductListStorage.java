package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListStorage implements ProductStorage {
    List<Product> productList;


    public ProductListStorage() {
        productList = new ArrayList<>();
    }

    @Override
    public void saveProduct(Product product) {
        productList.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }


    @Override
    public Product getProduct(String articleNumber) {
        for (Product product : productList) {
            if (product.getArticleNumber().equals(articleNumber))
                return product;
        }
        return null;
    }
}
