package com.itakademija.one;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

public class ButtonPanel extends JPanel {
    private final IconLoader iconLoader = new IconLoader(ButtonPanel.class);
    private final JButton leftButton;
    private final JButton centerButton;
    private final JButton rightButton;

    public ButtonPanel() {
        // A leftButton
        Icon rightIcon = iconLoader.loadIcon("right.gif");
        this.leftButton = new JButton("Disable middle button", rightIcon);
        this.leftButton.setActionCommand("disable");
        this.leftButton.setMnemonic(KeyEvent.VK_A);
        this.leftButton.setVerticalTextPosition(SwingConstants.CENTER);
        this.leftButton.setHorizontalTextPosition(SwingConstants.LEADING);
//        LeftButtonActionListener actionListener = new LeftButtonActionListener();
//        ActionListener actionListener = this::onControlButtonClick;
        this.leftButton.addActionListener(this::onControlButtonClick);

        Icon centerIcon = iconLoader.loadIcon("middle.gif");
        this.centerButton = new JButton("Middle button(Victim)", centerIcon);
        this.centerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.centerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        // D rightButton
        Icon leftIcon = iconLoader.loadIcon("left.gif");
        this.rightButton = new JButton("Enable middle button", leftIcon);
        this.rightButton.setMnemonic(KeyEvent.VK_D);
//        RightButtonActionListener rightButtonActionListener = new RightButtonActionListener();
//        ActionListener rightButtonActionListener = (actionEvent) -> onControlButtonClick(actionEvent);
        this.rightButton.addActionListener(this::onControlButtonClick);
        add(this.leftButton);
        add(this.centerButton);
        add(this.rightButton);
    }


    /**
     * Kontrolni dugmići su leftButton and rightButton tipa {@link JButton}.
     * Blok koda ove metode hoću da pretplatim i na jedan i na drugi kontrolni dugmić.
     *
     * @param e
     */
    public void onControlButtonClick(ActionEvent actionEvent) {
        System.out.println("Neko je kliknuo ili na lijevi ili na desni dugmić...");
        String command = actionEvent.getActionCommand();
        if (command != null && command.equals("disable")) {
            this.centerButton.setEnabled(false);
        } else {
            this.centerButton.setEnabled(true);
        }
    }

//
//    private Icon createIcon(String path) {
//        ClassLoader classLoader = ButtonPanel.class.getClassLoader();
//        URL url = classLoader.getResource(path);
//        if (url == null) {
//            System.err.println("Couldn't find resource: " + path);
//            return null;
//        }
//        return new ImageIcon(url);
//    }


    /*********** RJEŠENJE sa 2 ActionListenera *******/
    private class LeftButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Neko kliknuo na left button");
            ButtonPanel.this.centerButton.setEnabled(false);
        }
    }

    private class RightButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Neko kliknuo na right button");
            ButtonPanel.this.centerButton.setEnabled(true);
        }
    }
}
