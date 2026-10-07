package se.iths.richard.webshop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenPercentDiscountTest {

    @Test
    @DisplayName("Should return 10% off")
    public void testCalculatePrice() {
        Discount testDiscount = new TenPercentDiscount();
        double originalPrice = 100;

        double newPrice = testDiscount.calculatePrice(originalPrice);

        assertEquals(90, newPrice);

    }
}