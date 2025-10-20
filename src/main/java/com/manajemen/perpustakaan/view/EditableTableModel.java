// File: com/manajemen/perpustakaan/view/EditableTableModel.java
package com.manajemen.perpustakaan.view;

import javax.swing.table.DefaultTableModel;

public class EditableTableModel extends DefaultTableModel {
    private int editingRow = -1; // -1 berarti tidak ada baris yang diedit

    public EditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    // Metode untuk mengatur baris mana yang sedang diedit
    public void setEditingRow(int rowIndex) {
        this.editingRow = rowIndex;
        fireTableRowsUpdated(rowIndex, rowIndex); // Beri tahu tabel untuk menggambar ulang baris ini
    }

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