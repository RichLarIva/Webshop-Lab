package se.iths.richard.webshop.model;

import se.iths.richard.webshop.exceptions.IllegalPriceException;

public class Product {
    private String articleNumber;
    private String title;
    private double price;
    private String description;

    public Product(String articleNumber, String title, double price, String description) {
        if (articleNumber == null || articleNumber.isBlank() ||
                title == null || title.isBlank() ||
                description == null || description.isBlank()) {
            throw new IllegalArgumentException("Can't have blanks");
        }
        if (price < 0)
            throw new IllegalPriceException("Price can't be below 0");
        this.articleNumber = articleNumber;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public static Product fromFileLine(String line) {
        String[] fields = line.split(";");
        if (fields.length != 4) {
            throw new IllegalArgumentException("ERROR: INVALID PRODUCT LINE: " + line);
        }

        String articleNumber = fields[0];
        String title = fields[1];
        try {
            double price = Double.parseDouble(fields[2]);
            String description = fields[3];

            return new Product(articleNumber, title, price, description);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ERROR: COULDN'T PARSE PRICE ENDING PROGRAM");
        }
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toFileLine() {
        return articleNumber + ";" + title + ";" + price + ";" + description + System.lineSeparator();
    }
}
