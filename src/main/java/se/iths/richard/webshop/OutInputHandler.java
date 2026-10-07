package se.iths.richard.webshop;

public interface OutInputHandler {
    String prompt(String message);

    void info(String message);

    String menu();
}
