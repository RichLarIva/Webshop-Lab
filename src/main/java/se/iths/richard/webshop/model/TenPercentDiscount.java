package se.iths.richard.webshop.model;

public class TenPercentDiscount extends Discount {
    @Override
    public double calculatePrice(double originalPrice) {
        double discount = originalPrice / 10;
        double newPrice = originalPrice - discount;
        return newPrice;
    }
}
