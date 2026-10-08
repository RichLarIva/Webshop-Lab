package se.iths.richard.webshop;

import se.iths.richard.webshop.model.Discount;
import se.iths.richard.webshop.model.HalfPriceDiscount;
import se.iths.richard.webshop.model.Product;
import se.iths.richard.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {

    private final ProductStorage productStorage;
    private final OutInputHandler outInputHandler;

    public WebShopManager(ProductStorage productStorage, OutInputHandler outInputHandler) {
        this.productStorage = productStorage;
        this.outInputHandler = outInputHandler;
    }

    void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }


    public void startWebshop() {
        outInputHandler.info("TEST");
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
            String input = outInputHandler.prompt(outInputHandler.menu());
            switch (input.toLowerCase().trim()) {
                case "q":
                case "4":
                    isFinished = true;
                    break;
                case "1":
                    createProduct();
                    break;
                case "2":
                    listAllProducts();
                    break;
                case "3":
                    getSpecificProduct();
                    break;
            }
        }
    }

    private String readString(String prompt) {
        while (true) {
            String input = outInputHandler.prompt(prompt);
            if (input != null && !input.isBlank()) {
                return input;
            } else
                outInputHandler.info("Try again!");
        }
    }

    private void listAllProducts() {
        List<Product> productList = getProductsFromStorage();

        for (Product product : productList) {
            outInputHandler.info(product.toString());
        }
    }

    private void getSpecificProduct() {
        String articleNumber = readString("What product do you want: ");
        Product product = getProductFromStorage(articleNumber);
        if (product == null) {
            outInputHandler.info("No product found");
            return;
        }
        outInputHandler.info(product.toString());
    }

    private void createProduct() {
        String articleNumber = readString("Input products Article Number: ");

        String title = readString("Input products Title: ");
        double price = readDouble("Input products Price: ");
        String description = readString("Input products Description: ");

        Product product = new Product(articleNumber, title, price, description);
        saveProductToStorage(product);
    }

    private double readDouble(String prompt) {
        while (true) {
            String input = outInputHandler.prompt(prompt);
            if (input != null && !input.isBlank()) {
                try {
                    return Double.parseDouble(input.replace(',', '.'));
                } catch (NumberFormatException e) {
                    outInputHandler.info("Please enter a valid number!");
                }
            } else
                outInputHandler.info("Try again!");
        }
    }
}
