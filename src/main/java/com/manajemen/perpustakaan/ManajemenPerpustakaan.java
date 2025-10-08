package com.manajemen.perpustakaan;

import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;

public class ManajemenPerpustakaan {
    public static void main(String[] args) {
        TransaksiPeminjamanView transaksiPeminjamanView =  new TransaksiPeminjamanView();
        new TransaksiPeminjamanController(transaksiPeminjamanView);

        transaksiPeminjamanView.setVisible(true);
    }
}
