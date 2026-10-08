package se.iths.richard.webshop;

import javax.swing.*;

public class JOptionPaneOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {
        return "";
    }

    @Override
    public void info(String message) {
        JOptionPane test = new JOptionPane();
        test.createDialog(message);
    }

    @Override
    public String menu() {
        return "";
    }
}
