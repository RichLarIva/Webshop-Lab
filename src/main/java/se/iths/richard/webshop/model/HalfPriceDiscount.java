package se.iths.richard.webshop.model;

public class HalfPriceDiscount extends Discount {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice / 2;
    }
}
