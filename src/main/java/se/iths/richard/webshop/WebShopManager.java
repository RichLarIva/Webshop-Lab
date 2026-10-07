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
            4/q. Quit Application
            Choice: """;
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
            String input = IO.readln(MENU);

            switch (input.toLowerCase().trim()) {
                case "q":
                case "4":
                    isFinished = true;
                    break;
                case "1":
                    String articleNumber = readString("Input products Article Number: ");
                    String title = readString("Input products Title: ");
                    double price = readDouble("Input products Price: ");
                    String description = readString("Input products Description: ");

                    Product product = new Product(articleNumber, title, price, description);
                    saveProductToStorage(product);
            }
        }
    }

    private String readString(String prompt) {
        while (true) {
            String input = IO.readln(prompt);

            if (input != null && !input.isBlank()) {
                return input;
            } else
                IO.println("Try again!");
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            String input = IO.readln(prompt);

            if (input != null && !input.isBlank()) {
                try {
                    return Double.parseDouble(input.replace(',', '.'));
                } catch (NumberFormatException e) {
                    IO.println("Please enter a valid number!");
                }
            } else
                IO.println("Try again!");
        }
    }
}
