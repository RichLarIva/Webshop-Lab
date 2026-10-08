package se.iths.richard.webshop;

import javax.swing.*;

public class JOptionPaneOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public String menu() {
        return """
                1. Add Product
                2. List all products
                3. Show info about one product
                4/q. Quit
                """;
    }
}
