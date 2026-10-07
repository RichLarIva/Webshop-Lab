package se.iths.richard.webshop.exceptions;

public class IllegalPriceException extends IllegalArgumentException {
    public IllegalPriceException()
    {
        super("Illegal Price");
    }
    public IllegalPriceException(String message)
    {
        super(message);
    }
}
