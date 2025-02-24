package com.itakademija.one;

import javax.swing.*;

/**
 * <li>1. Kontejner
 *    JFrame - prozor
 *    JPanel - staviti u prozor
 * </li>
 * <li>
 *     2. LayoutManager
 *     JFrame -> BorderLayout
 *     JPanel -> FlowLayout
 * </li>
 * <li>
 *     3. GUI kontrole
 *      JButton, JTextField, JCheckBox, JRadioButton, JToggleButton
 * </li>
 */
public class ButtonDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Runnable runnable = ButtonDemo::createAndShowGUI;
        SwingUtilities.invokeLater(runnable);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        System.out.println(Thread.currentThread().getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//magic number 3
        frame.setTitle("Button Demo");
//        ButtonPanel panel = new ButtonPanel();
//        JPanel panel = new ListDialogPanel();
        frame.setContentPane(new CheckBoxPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
