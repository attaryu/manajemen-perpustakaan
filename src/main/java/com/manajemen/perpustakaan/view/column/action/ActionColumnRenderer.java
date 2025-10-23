package com.manajemen.perpustakaan.view.column.action;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionColumnRenderer implements TableCellRenderer {

    private final ActionPanel panel;
    private final JPanel emptyPanel;

    public ActionColumnRenderer() {
        // Buat instance sekali di constructor
        this.panel = new ActionPanel();
        this.emptyPanel = new JPanel();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        // Validasi row
        if (row < 0 || row >= table.getRowCount()) {
            return this.emptyPanel;
        }

        // Cek apakah data di kolom pertama ada dan tidak kosong
        Object firstColumnValue = table.getValueAt(row, 0);

        if (firstColumnValue != null && !firstColumnValue.toString().trim().isEmpty()) {
            if (isSelected) {
                this.panel.setBackground(table.getSelectionBackground());
            } else {
                this.panel.setBackground(table.getBackground());
            }

            return this.panel;
        } else {
            this.emptyPanel.setBackground(table.getBackground());

            return this.emptyPanel;
        }
    }
}