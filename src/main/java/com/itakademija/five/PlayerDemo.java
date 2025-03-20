package com.itakademija.five;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class PlayerDemo {
    //main Thread -> main funkciju on izvršava
    public static void main(String[] args) throws Exception {
        String lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
//        UIManager.setLookAndFeel(lookAndFeelClassName);
//        UIManager.setLookAndFeel(new FlatDarkLaf());

        Runnable worker = PlayerDemo::createAndShowGUI;
        SwingUtilities.invokeLater(worker);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Player Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new PlayerTablePanel2();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
