package com.itakademija.one;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * StringBuilder stringBilder = new StringBuilder();
 * stringBilder.append("AMksm");
 * stringBilder.append("sdsk");
 * <p>
 * DVA Thread -> StringBuffer je njegov napredniji rođak koji radi u multithreading okruženju.
 * </p>
 * <p>
 * FlowLayout....
 *
 * <p>
 * BorderLayout
 * </p>
 */
public class CheckBoxPanel extends JPanel {
    //slika kao ikonica
    private final JLabel pictureLabel = new JLabel();
    //geek-cght.gif
    private final StringBuffer pictureChoice = new StringBuffer("cght");

    private final JCheckBox chinCheckBox = new JCheckBox("Chin");
    private final JCheckBox glassesCheckBox = new JCheckBox("Glasses");
    private final JCheckBox hairCheckBox = new JCheckBox("Hair");
    private final JCheckBox teethCheckBox = new JCheckBox("Teeth");


    public CheckBoxPanel() {
        setLayout(new BorderLayout());
        CheckBoxListener checkBoxListener = new CheckBoxListener();
        this.chinCheckBox.setSelected(true);
        this.chinCheckBox.addItemListener(checkBoxListener);
        this.glassesCheckBox.setSelected(true);
        this.glassesCheckBox.addItemListener(checkBoxListener);
        this.hairCheckBox.setSelected(true);
        this.hairCheckBox.addItemListener(checkBoxListener);
        this.teethCheckBox.setSelected(true);
        this.teethCheckBox.addItemListener(checkBoxListener);

        JPanel checkBoxPanel = new JPanel(new GridLayout(0, 1));
        checkBoxPanel.add(this.chinCheckBox);
        checkBoxPanel.add(this.glassesCheckBox);
        checkBoxPanel.add(this.hairCheckBox);
        checkBoxPanel.add(this.teethCheckBox);

        updatePicture();

        add(checkBoxPanel, BorderLayout.LINE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void updatePicture() {
        IconLoader iconLoader = new IconLoader(CheckBoxPanel.class);
        //geek-cght.gif
        Icon imageIcon = iconLoader.loadIcon("geek/geek-" + pictureChoice.toString() + ".gif");
        pictureLabel.setIcon(imageIcon);
    }

    // JCheckBox <---- ItemEvent ---> CheckBoxListener
    private class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            int index = 0;
            char c = '-';
            Object source = event.getSource();
            if(source == chinCheckBox){
                index = 0;
                c = 'c';
            } else if (source == glassesCheckBox) {
                index = 1;
                c = 'g';
            } else if (source == hairCheckBox) {
                index = 2;
                c = 'h';
            } else if (source == teethCheckBox) {
                index = 3;
                c = 't';
            }
            if(event.getStateChange() == ItemEvent.DESELECTED){
                c = '-';
            }
            pictureChoice.setCharAt(index, c);
            updatePicture();
        }
    }
}
