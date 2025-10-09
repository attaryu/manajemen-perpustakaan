package com.manajemen.perpustakaan;

import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;

public class ManajemenPerpustakaan {
    public static void main(String[] args) {
        TransaksiPeminjamanView transaksiPeminjamanView =  new TransaksiPeminjamanView();
        TambahPeminjamanView tambahPeminjamanView =  new TambahPeminjamanView();
        new TransaksiPeminjamanController(transaksiPeminjamanView, tambahPeminjamanView);

        transaksiPeminjamanView.setVisible(true);
    }
}
