package se.iths.richard.webshop.exceptions;

public class IllegalEmptyProductException extends RuntimeException {
    public IllegalEmptyProductException(String message) {
        super(message);
    }
}
