package com.itakademija.five;

import com.itakademija.four.Demo;
import com.itakademija.three.sport.dao.player.PlayerInfo;
import com.itakademija.three.sport.dao.player.PlayerInfoDao;

import javax.swing.*;
import java.util.List;

public class PlayerDemo {
    public static void main(String[] args) {
//        Kutija<String, Integer> kutija = new Kutija<>();
//        Kutija<?, ? super Double> kutija2 = new Kutija<>();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Runnable runnable = PlayerDemo::createAndShowGUI;
        SwingUtilities.invokeLater(runnable);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Table Demonstration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new SimpleTablePanel();
//        JPanel panel = new TablePanel();
        JPanel panel = new PlayerTablePanel();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
