package com.manajemen.perpustakaan.view.column.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.manajemen.perpustakaan.view.column.IdGetter;

public class ActionColumnEditor extends AbstractCellEditor implements TableCellEditor {

    private final ActionPanel panel;
    private int row;
    private ActionCallback actionCallback;
    private IdGetter idGetter;

    // --- KONSTRUKTOR DIMULAI ---
    public ActionColumnEditor(ActionCallback actionCallback, IdGetter idGetter) {
        panel = new ActionPanel();
        this.actionCallback = actionCallback;
        this.idGetter = idGetter;

        // Listener untuk tombol Edit
        panel.cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();

                String id = ActionColumnEditor.this.getId();

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

                String id = ActionColumnEditor.this.getId();

                if (ActionColumnEditor.this.actionCallback != null) {
                    ActionColumnEditor.this.actionCallback.onDelete(id);
                }
            }
        }); // Listener Delete selesai
    }

    public String getId() {
        return this.idGetter.getId(this.row);
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
        this.row = row;
        panel.setBackground(table.getSelectionBackground());

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null; // Nilai balikan tidak penting untuk kasus ini
    }
}