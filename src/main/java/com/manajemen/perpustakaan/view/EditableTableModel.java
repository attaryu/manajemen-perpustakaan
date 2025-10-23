// File: com/manajemen/perpustakaan/view/EditableTableModel.java
package com.manajemen.perpustakaan.view;

import javax.swing.table.DefaultTableModel;

/**
 * Custom table model yang mendukung editing row tertentu.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class EditableTableModel extends DefaultTableModel {
    /** Index row yang sedang diedit, -1 jika tidak ada */
    private int editingRow = -1;

    /**
     * Konstruktor EditableTableModel.
     * 
     * @param data data table
     * @param columnNames nama kolom table
     */
    public EditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    /**
     * Mengatur row mana yang sedang dalam mode editing.
     * 
     * @param rowIndex index row yang akan diedit
     */
    public void setEditingRow(int rowIndex) {
        this.editingRow = rowIndex;
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    /**
     * Mendapatkan index row yang sedang diedit.
     * 
     * @return index row yang sedang diedit, -1 jika tidak ada
     */
    public int getEditingRow() {
        return this.editingRow;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Kolom "Aksi" (indeks 7) selalu bisa di-klik
        if (column == 7) {
            return true;
        }
        // Jika baris ini adalah baris yang sedang diedit, izinkan pengeditan di kolom data
        if (row == editingRow) {
            // Blokir pengeditan pada kolom tertentu jika perlu, misal NRP (indeks 1)
            return column != 1 && column != 8; // Izinkan edit di semua kolom kecuali NRP dan id
        }
        // Jika tidak, jangan izinkan pengeditan
        return false;
    }
}