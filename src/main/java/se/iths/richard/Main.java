package se.iths.richard;

import se.iths.richard.webshop.*;

public class Main {
    static void main() {
        ProductStorage productStorage = new ProductFileStorage();
        OutInputHandler inputHandler = new JOptionPaneOutInputHandler();
        WebShopManager webShopManager = new WebShopManager(productStorage, inputHandler);

        webShopManager.startWebshop();
    }
}
