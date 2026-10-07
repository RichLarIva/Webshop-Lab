package se.iths.richard.webshop.model;

public abstract class Discount {
    protected String description;

    public abstract double calculatePrice(double originalPrice);
}
