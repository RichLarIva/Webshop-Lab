package se.iths.richard.webshop.model;

import se.iths.richard.webshop.exceptions.IllegalPriceException;

public class HalfPriceDiscount extends Discount {

    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0)
            throw new IllegalPriceException("Price can't be below 0");
        return originalPrice / 2;
    }
}
