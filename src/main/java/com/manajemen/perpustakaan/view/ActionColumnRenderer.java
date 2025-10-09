package com.manajemen.perpustakaan.view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionColumnRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Cek apakah data di kolom pertama (indeks 0) ada dan tidak kosong
        Object firstColumnValue = table.getValueAt(row, 0);

        if (firstColumnValue != null && !firstColumnValue.toString().trim().isEmpty()) {
            // Jika baris berisi data, tampilkan panel tombol
            ActionPanel panel = new ActionPanel();

            // Atur warna latar belakang agar sesuai dengan seleksi tabel
            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(table.getBackground());
            }

            return panel;
        } else {
            // Jika baris kosong, tampilkan panel kosong
            return new JPanel();
        }
    }
}