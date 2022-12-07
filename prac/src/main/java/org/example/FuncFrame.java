package org.example;

import javax.swing.*;

public class FuncFrame {
    private JFrame frame;
    private JPanel panel;

    private ClockShop shop;

    public FuncFrame(ClockShop market) {
        shop = market;
        frame = new JFrame();
        panel = new JPanel();
        frame.getContentPane();
        frame.setSize(500, 350);
        frame.setLocation(200, 100);
        frame.setTitle("Functions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
