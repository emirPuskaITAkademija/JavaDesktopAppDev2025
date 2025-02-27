package com.itakademija.two;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// DZ : Prepisati ovaj dijalog da se zove SelectOptionDialog
// E -> int -> Integer
// java.lang.Object
public class SelectOptionDialog<E> extends JDialog{


    private static Object selectedValue;
    //java.awt
    private final JList<E> list;
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton setButton = new JButton("Set");


    private SelectOptionDialog(Frame frame,
                               Component locationComp,
                               String labelText,
                               String title,
                               E[] data,
                               E initialValue,
                               String longValue) {
        super(frame, title, true);
        this.list = new JList<>(data);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.list.setVisibleRowCount(-1);
        JScrollPane scrollPane = new JScrollPane(this.list);
        scrollPane.setPreferredSize(new Dimension(250, 80));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel(labelText);
        label.setLabelFor(list);
        listPanel.add(label);
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        listPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createGlue());
        ActionListener buttonListener = this::actionPerformed;
        cancelButton.addActionListener(buttonListener);
        setButton.addActionListener(buttonListener);
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(setButton);

        Container container = getContentPane();
        container.add(listPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.PAGE_END);

        setValue(initialValue);
        pack();
        setLocationRelativeTo(locationComp);
    }

    public void setValue(E newValue) {
        selectedValue = newValue;
        this.list.setSelectedValue(selectedValue, true);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Set")) {
            selectedValue = list.getSelectedValue();
        }
        SelectOptionDialog.this.setVisible(false);
    }

    public static Object showDialog(Component frameComponent,
                                    Component locationComp,
                                    String labelText,
                                    String title,
                                    String[] data,
                                    String initialValue,
                                    String longValue) {
        Frame frame = JOptionPane.getFrameForComponent(frameComponent);
        SelectOptionDialog selectNameDialog = new SelectOptionDialog(frame,
                frameComponent,
                labelText,
                title,
                data,
                initialValue,
                longValue);
        selectNameDialog.setVisible(true);
        return selectedValue;
    }
}
