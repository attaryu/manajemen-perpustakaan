package com.manajemen.perpustakaan;

import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.view.dummy.TransaksiPeminjamanView;

public class ManajemenPerpustakaan {
    public static void main(String[] args) {
        TransaksiPeminjamanView transaksiPeminjamanView = new TransaksiPeminjamanView();
        TransaksiPeminjamanController transaksiPeminjamanController = new TransaksiPeminjamanController();
    }
}
