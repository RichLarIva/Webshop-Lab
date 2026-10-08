package se.iths.richard.webshop;

public class ConsoleOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {
        String input = IO.readln(message);
        return input;
    }

    @Override
    public void info(String message) {
        IO.println(message);
    }

    @Override
    public String menu() {
        return """
                1. Add Product
                2. List all products
                3. Show info about one product
                4/q. Quit
                Choice: """;
    }
}
