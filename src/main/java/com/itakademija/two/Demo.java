package com.itakademija.two;

import javax.swing.*;
import java.awt.*;

/**
 * Tri vrste GUI komponenti:
 * <li>1. Kontejnerske:
 *      JFrame -> prozor koji ujedno agregira sve druge GUI kontejnere i GUI kontrole
 *      JPanel
 * </li>
 * <li>2. Kontrole
 *     JButton,
 *     JCheckBox,
 *     ListDialog,
 *     JComboBox,
 *     JRadioButton
 * </li>
 * <li>3. LayoutManager
 *       FrameLayout -> BorderLayout (default)
 *       JPanel -> FlowLayout(default)
 *                 GridLayout
 *                 BoxLayout X Y
 * </li>
 */
public class Demo {
    //Thread -> main GUI -> Thread drugom
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Runnable runnable = Demo::createAndShoGUI;
        SwingUtilities.invokeLater(runnable);
    }

    private static void createAndShoGUI(){
        System.out.println(Thread.currentThread().getName());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new CheckBoxPanel();
//        new SelectNamePanel(frame)
        JPanel panel = new ComboBoxPanel();
        frame.setContentPane(panel);
        frame.setTitle("Graphical User Interface Demo");
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setVisible(true);
    }
}
