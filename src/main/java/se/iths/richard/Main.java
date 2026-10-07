package se.iths.richard;

import se.iths.richard.webshop.ProductFileStorage;
import se.iths.richard.webshop.ProductStorage;
import se.iths.richard.webshop.WebShopManager;

public class Main {
    static void main() {
        ProductStorage productStorage = new ProductFileStorage();
        WebShopManager webShopManager = new WebShopManager(productStorage);

        webShopManager.startWebshop();
    }
}
