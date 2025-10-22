package com.manajemen.perpustakaan;

import javax.swing.SwingUtilities;

import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;
import com.manajemen.perpustakaan.view.ViewUpdateData;

public class ManajemenPerpustakaan {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TransaksiPeminjamanView transaksiPeminjamanView = new TransaksiPeminjamanView();
            TambahPeminjamanView tambahPeminjamanView = new TambahPeminjamanView();
            ViewUpdateData updateView = new ViewUpdateData();

            new TransaksiPeminjamanController(transaksiPeminjamanView, tambahPeminjamanView, updateView);

            transaksiPeminjamanView.setVisible(true);
        });
    }
}
