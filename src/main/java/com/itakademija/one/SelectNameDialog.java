//package com.itakademija.one;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//
//public class SelectNameDialog extends JDialog {
//    private static SelectNameDialog dialog;
//    private JList<String> listOfNames;
//
//    private SelectNameDialog(Frame frame,
//                             Component parent,
//                             Component location,
//                             String labelText,
//                             String title,
//                             String[] possibleValues,
//                             String initialValue,
//                             String longValue) {
//        super(frame, title, true);
//
//        JButton cancelButton = new JButton("Cancel");
//        cancelButton.addActionListener(this::onCancelClick);
//
//
//        JButton setButton = new JButton("Set");
//        setButton.addActionListener(this::onSetClick);
//
//        getRootPane().setDefaultButton(setButton);
//    }
//
//    private void onCancelClick(ActionEvent e) {
//        //TODO
//    }
//
//    private void onSetClick(ActionEvent e) {
//        //TODO
//    }
//
//
//    public static String showDialog(Component parent,
//                                    Component location,
//                                    String labelText,
//                                    String title,
//                                    String[] possibleValues,
//                                    String initialValue,
//                                    String longValue) {
//        Frame frame = JOptionPane.getFrameForComponent(parent);
//        dialog = new SelectNameDialog(frame, frame, location, labelText, title, possibleValues, initialValue, longValue);
//    }
//}
