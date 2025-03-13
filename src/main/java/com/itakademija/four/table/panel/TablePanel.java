package com.itakademija.four.table.panel;

import com.itakademija.four.table.cell.ColorCellEditor;
import com.itakademija.four.table.cell.ColorCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablePanel extends JPanel {

    private final JTable table;

    private final JTextField filterTextField;
    private final JTextField statusTextField = new JTextField();
    private final TableRowSorter<TableModel> tableRowSorter;

    private final ButtonGroup buttonGroup;

    private JCheckBox rowCheckBox;
    private JCheckBox columnCheckBox;
    private JCheckBox cellCheckBox;

    private JTextArea outputTextArea;

    public TablePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //FORMA sa filter text field input
        JPanel formPanel = new JPanel(new GridLayout(1, 1));
        JLabel filterLabel = new JLabel("Filter:", SwingConstants.TRAILING);
        formPanel.add(filterLabel);
        filterTextField = new JTextField();
        Document document = filterTextField.getDocument();
        document.addDocumentListener(new FilterDocumentListener());

        formPanel.add(filterTextField);
        add(formPanel);
        //TABLE
        TableModel customTableModel = new CustomTableModel();
        this.table = new JTable(customTableModel);
        this.tableRowSorter = new TableRowSorter<>(customTableModel);
        this.table.setRowSorter(this.tableRowSorter);
        this.table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        this.table.setFillsViewportHeight(true);
        this.table.setDefaultRenderer(Color.class, new ColorCellRenderer());
        this.table.setDefaultEditor(Color.class, new ColorCellEditor());
        add(new JScrollPane(this.table));
        //
        MainActionListener mainActionListener = new MainActionListener();
        //RADIO BUTTONs
        add(new JLabel("Selection Mode"));
        this.buttonGroup = new ButtonGroup();
        JRadioButton multipleIntervalSelectionRadioButton = createRadioButton("Multiple Interval Selection", buttonGroup);
        multipleIntervalSelectionRadioButton.setSelected(true);
        multipleIntervalSelectionRadioButton.addActionListener(mainActionListener);
        add(multipleIntervalSelectionRadioButton);
        JRadioButton singleSelectionRadioButton = createRadioButton("Multiple Interval Selection", buttonGroup);
        singleSelectionRadioButton.addActionListener(mainActionListener);
        add(singleSelectionRadioButton);
        JRadioButton singleIntervalSelectionRadioButton = createRadioButton("Multiple Interval Selection", buttonGroup);
        singleIntervalSelectionRadioButton.addActionListener(mainActionListener);
        add(singleIntervalSelectionRadioButton);
        //CHECK BOX
        add(new JLabel("Selection Options"));
        this.rowCheckBox = createCheckBox("Row Selection");
        this.rowCheckBox.addActionListener(mainActionListener);
        add(this.rowCheckBox);
        this.columnCheckBox = createCheckBox("Column Selection");
        this.columnCheckBox.addActionListener(mainActionListener);
        add(this.columnCheckBox);
        this.cellCheckBox = createCheckBox("Cell Selection");
        this.cellCheckBox.setSelected(true);
        this.cellCheckBox.addActionListener(mainActionListener);
        add(this.cellCheckBox);

        this.outputTextArea = new JTextArea(5, 40);
        this.outputTextArea.setEditable(false);
        add(new JScrollPane(this.outputTextArea));

    }

    private class FilterDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            filter();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            filter();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            filter();
        }
    }


    private void filter(){
        RowFilter<TableModel, Object> rowFilter = null;
        try{
            int columnIndex = 0;
            rowFilter = RowFilter.regexFilter(this.filterTextField.getText(), columnIndex);
        }catch (Exception e){
            return;
        }
        tableRowSorter.setRowFilter(rowFilter);
    }

    private JRadioButton createRadioButton(String text, ButtonGroup group) {
        JRadioButton radioButton = new JRadioButton(text);
        group.add(radioButton);
        return radioButton;
    }

    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        return checkBox;
    }

    private void println(String text){
        this.outputTextArea.append(text + "\n");
    }


    private class CustomTableModel extends AbstractTableModel {
        private String[] columnNames = {"First Name", "Last Name", "Sport", "# of years", "Vegetarian", "Color"};
        private Object[][] data = {
                {"Tarik", "Fejzić", "Nogomet", 5, true, Color.BLACK},
                {"Nikola", "Šipka", "Rukomet", 4, false, Color.RED},
                {"Jovan", "Carević", "Košarka", 15, true, Color.BLUE},
                {"Gordan", "Grgić", "Vaterpolo", 15, false, Color.GREEN},
                {"Danko", "Divković", "Šah", 15, true, Color.YELLOW},
        };

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            data[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return data[0][columnIndex].getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 2;
        }
    }


    private class MainActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            System.out.println("Komanda : " + command);
            TablePanel.this.println("Komanda : " + command);
        }
    }
}
