package com.itakademija.four.table.panel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ovdje demonstriramo upotrebu tabele bez baze podataka.
 *
 * JTable(Object[][] rowData, Object[] columnNames)
 * <li></li>
 * JTable(Vector<Vector<>> rowData, Vector<> columnNames)
 */
public class SimpleTablePanel extends JPanel {

    private String[] columnNames = {"First Name", "Last Name", "Sport", "# of years", "Vegetarian", "Color"};
    private Object[][] data = {
            {"Tarik", "Fejzić", "Nogomet", 5, true, Color.BLACK},
            {"Nikola", "Šipka", "Rukomet", 4, false, Color.RED},
            {"Jovan", "Carević", "Košarka", 15, true, Color.BLUE},
            {"Gordan", "Grgić", "Vaterpolo", 15, false, Color.GREEN},
            {"Danko", "Divković", "Šah", 15, true, Color.YELLOW},
    };

    private final JTable table;

    public SimpleTablePanel() {
        setLayout(new GridLayout(1, 0));
        this.table = new JTable(data, columnNames);
        this.table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        this.table.setFillsViewportHeight(true);
        this.table.addMouseListener(new SimpleTableListener());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private class SimpleTableListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JTable jTable = (JTable) e.getSource();
            TableModel model = jTable.getModel();
            int selectedRow = jTable.getSelectedRow();
            int selectedColumn = jTable.getSelectedColumn();
            System.out.println(model.getValueAt(selectedRow, selectedColumn));
//            int numberOfRows = jTable.getRowCount();
//            int numberOfColumns = jTable.getColumnCount();
//            for(int i=0; i<numberOfRows; i++) {
//                for(int j=0; j<numberOfColumns; j++) {
//                    System.out.println(model.getValueAt(i, j));
//                }
//            }
        }
    }
}
