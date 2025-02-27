package com.itakademija.two.combo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditableComboBoxPanel extends JPanel {
    private final String[] datePatterns = {
            "dd MMMMM yyyy",
            "dd.MM.yy",
            "MM/dd/yyyy",
            "yyyy.MM.dd G 'at' hh:mm:ss z",
            "yyyy.MMMMM.dd G hh:mm aaa"
    };

    private JLabel resultLabel;
    private String currentDateTimePattern;

    public EditableComboBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.currentDateTimePattern = datePatterns[0];

        JLabel patternLabel1 = new JLabel("Unesi pattern datum/vremena ili");
        JLabel patternLabel2 = new JLabel("odaberi već postojeći patern datuma/vremena");
        JComboBox<String> patternComboBox = new JComboBox<>(datePatterns);
        DateTimePatternActionListener actionListener = new DateTimePatternActionListener();
        patternComboBox.addActionListener(actionListener);
        patternComboBox.setEditable(true);

        //lijevo
        JLabel patternResultLabel = new JLabel("Trenutni datum/vrijem formatirano", JLabel.LEADING);
        this.resultLabel = new JLabel(" ");
        this.resultLabel.setForeground(Color.WHITE);
        Border lineBorder = BorderFactory.createLineBorder(Color.WHITE);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        this.resultLabel.setBorder(compoundBorder);

        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        patternComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternPanel.add(patternComboBox);


        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(0,1));
        resultPanel.add(patternResultLabel);
        resultPanel.add(this.resultLabel);


        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.formatDateTime(currentDateTimePattern);
    }


    private void formatDateTime(String dateTimePattern){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimePattern);
        try{
            String formattedDate = formatter.format(date);
            this.resultLabel.setText(formattedDate);
        }catch (Exception e){
            this.resultLabel.setForeground(Color.RED);
            this.resultLabel.setText(e.getMessage());
            System.err.println(e.getMessage());
        }
    }

    private class DateTimePatternActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JComboBox<String> patternComboBox = (JComboBox<String>) event.getSource();
            String newDateTimePatternSelection = (String) patternComboBox.getSelectedItem();
            formatDateTime(newDateTimePatternSelection);
        }
    }
}
