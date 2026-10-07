package se.iths.richard.webshop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.iths.richard.webshop.exceptions.IllegalPriceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HalfPriceDiscountTest {

    @ParameterizedTest
    @DisplayName("Should return 10% off, runs multiple parameters")
    @ValueSource(doubles = {100, 50, 10, 1, 200, 500, 25034.25})
    public void testCalculatePrice(double originalPrice) {
        Discount testDiscount = new HalfPriceDiscount();

        double expectedPrice = originalPrice * 0.5;
        double newPrice = testDiscount.calculatePrice(originalPrice);

        assertEquals(expectedPrice, newPrice);

    }

    @Test
    @DisplayName("Should return 0 when original price is 0")
    void testCalculatePriceWithZero() {
        Discount testDiscount = new HalfPriceDiscount();

        double originalPrice = 0;
        double newPrice = testDiscount.calculatePrice(originalPrice);

        assertEquals(0, newPrice);
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalPriceException when price is negative")
    @ValueSource(doubles = {-1, -10, -100, -0.1})
    void testCalculatePriceWithNegativePrice(double originalPrice) {
        Discount testDiscount = new HalfPriceDiscount();
        assertThrows(IllegalPriceException.class, () -> testDiscount.calculatePrice(originalPrice));
    }
}