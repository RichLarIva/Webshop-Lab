package se.iths.richard.webshop.model;

import se.iths.richard.webshop.exceptions.IllegalPriceException;

public class TenPercentDiscount extends Discount {
    public TenPercentDiscount() {
        super.description = "Gives 10% off of price";
    }

    @Override
    public double calculatePrice(double originalPrice) {
        if(originalPrice < 0)
            throw new IllegalPriceException("Price can't be below 0");

        double discount = originalPrice / 10;
        double newPrice = originalPrice - discount;
        return newPrice;
    }

    public String getDescription()
    {
        return super.description;
    }
}
