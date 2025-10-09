package com.manajemen.perpustakaan.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionColumnEditor extends AbstractCellEditor implements TableCellEditor {

    private final ActionPanel panel;
    private JTable table;
    private int row;

    // --- KONSTRUKTOR DIMULAI ---
    public ActionColumnEditor() {
        panel = new ActionPanel();

        // Listener untuk tombol Edit
        panel.cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                String nama = table.getValueAt(row, 0).toString();
                JOptionPane.showMessageDialog(null, "Tombol EDIT diklik untuk baris: " + (row + 1) + "\nNama: " + nama);
                // TODO: Tambahkan logika untuk membuka form edit di sini
            }
        }); // Listener Edit selesai

        // Listener untuk tombol Delete
        panel.cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                int response = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(row);
                }
            }
        }); // Listener Delete selesai
    }
    // --- KONSTRUKTOR SELESAI ---

    // --- METODE-METODE OVERRIDE (YANG SEHARUSNYA) ---

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