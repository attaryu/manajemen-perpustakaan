package com.manajemen.perpustakaan.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ActionColumnEditor extends AbstractCellEditor implements TableCellEditor {

    private final ActionPanel panel;
    private JTable table;
    private int row;
    private ActionCallback actionCallback;

    // --- KONSTRUKTOR DIMULAI ---
    public ActionColumnEditor(ActionCallback actionCallback) {
        panel = new ActionPanel();
        this.actionCallback = actionCallback;

        // Listener untuk tombol Edit
        panel.cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();

                String id = getTransaksiId();

                if (ActionColumnEditor.this.actionCallback != null) {
                    ActionColumnEditor.this.actionCallback.onEdit(id);
                }
            }
        }); // Listener Edit selesai

        // Listener untuk tombol Delete
        panel.cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();

                String id = getTransaksiId();

                if (ActionColumnEditor.this.actionCallback != null) {
                    ActionColumnEditor.this.actionCallback.onDelete(id);
                }
            }
        }); // Listener Delete selesai
    }

    private String getTransaksiId() {
        return table.getValueAt(row, 8).toString();
    }

    // Ini adalah metode yang Anda salah letakkan sebelumnya
    @Override
    public boolean isCellEditable(java.util.EventObject anEvent) {
        if (anEvent instanceof java.awt.event.MouseEvent) {
            JTable sourceTable = (JTable) anEvent.getSource();
            int clickedRow = sourceTable.rowAtPoint(((java.awt.event.MouseEvent) anEvent).getPoint());

            Object firstColumnValue = sourceTable.getValueAt(clickedRow, 0);
            if (firstColumnValue != null && !firstColumnValue.toString().trim().isEmpty()) {
                return super.isCellEditable(anEvent);
            }
        }
        return false;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;

        panel.setBackground(table.getSelectionBackground());
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null; // Nilai balikan tidak penting untuk kasus ini
    }
}