package se.iths.richard.webshop.model;

public class Product {
    private String articleNumber;
    private String title;
    private double price;
    private String description;

    public Product(String articleNumber, String title, double price, String description) {
        this.articleNumber = articleNumber;
        this.title = title;
        this.price = price;
        this.description = description;
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

    public static Product fromFileLine(String line)
    {
        String[] splittedLine = line.split(";");
        String articleNumber = splittedLine[0];
        String title = splittedLine[1];
        try
        {
            double price = Double.parseDouble(splittedLine[2]);
            String description = splittedLine[3];

            return new Product(articleNumber, title, price, description);
        }
        catch (NumberFormatException e)
        {
            throw new NumberFormatException("ERROR: COULDN'T PARSE PRICE ENDING PROGRAM");
        }
    }
}
