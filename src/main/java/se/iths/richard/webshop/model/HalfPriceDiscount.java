package se.iths.richard.webshop.model;

import se.iths.richard.webshop.exceptions.IllegalPriceException;

public class HalfPriceDiscount extends Discount {

    public HalfPriceDiscount() {
        super.description = "Gives 10% off of price";
    }

    public String getDescription() {
        return super.description;
    }

    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0)
            throw new IllegalPriceException("Price can't be below 0");
        return originalPrice / 2;
    }
}
