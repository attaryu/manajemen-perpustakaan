package com.manajemen.perpustakaan.view.column.action;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class FlexibleActionColumnRenderer implements TableCellRenderer {
    
    private final FlexibleActionPanel panel;
    private final JPanel emptyPanel;
    
    public FlexibleActionColumnRenderer(List<ActionButton> actions) {
        this.panel = new FlexibleActionPanel(actions);
        this.emptyPanel = new JPanel();
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        // Validasi row
        if (row < 0 || row >= table.getRowCount()) {
            return emptyPanel;
        }
        
        // Cek apakah data di kolom pertama ada dan tidak kosong
        Object firstColumnValue = table.getValueAt(row, 0);
        
        if (firstColumnValue != null && !firstColumnValue.toString().trim().isEmpty()) {
            // Set background sesuai selection
            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(table.getBackground());
            }
            
            return panel;
        } else {
            emptyPanel.setBackground(table.getBackground());
            return emptyPanel;
        }
    }
}