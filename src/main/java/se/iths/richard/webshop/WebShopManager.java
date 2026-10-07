package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Discount;
import se.iths.richard.webshop.model.HalfPriceDiscount;
import se.iths.richard.webshop.model.Product;
import se.iths.richard.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private static final String MENU = """
            1. Add Product
            2. List all Products
            3. Show info about one Product
            4/q. Quit Application""";
    private final ProductStorage productStorage;

    public WebShopManager(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    public List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    public Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    public void startWebshop() {
        String discountCode = "";
        Discount discount = null;

        switch (discountCode) {
            case "halfprice":
                discount = new HalfPriceDiscount();
                break;
            default:
                discount = new TenPercentDiscount();
        }
        boolean isFinished = false;
        while (!isFinished) {
            String input = IO.readln();
        }
    }
}
