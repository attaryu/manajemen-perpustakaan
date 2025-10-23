package com.manajemen.perpustakaan;

import javax.swing.SwingUtilities;

import com.manajemen.perpustakaan.controller.BukuController;
import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;
import com.manajemen.perpustakaan.view.BukuView;
import com.manajemen.perpustakaan.view.TambahBukuView;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;
import com.manajemen.perpustakaan.view.UpdateBukuView;
import com.manajemen.perpustakaan.view.UpdatePeminjamanView;

public class ManajemenPerpustakaan {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaRepository mahasiswaRepo = new MahasiswaRepository();
            BukuRepository bukuRepo = new BukuRepository();
            EksemplarBukuRepository eksemplarBukuRepo = new EksemplarBukuRepository();
            TransaksiPeminjamanRepository transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();

            TransaksiPeminjamanView transaksiPeminjamanView = new TransaksiPeminjamanView();
            TambahPeminjamanView tambahPeminjamanView = new TambahPeminjamanView();
            UpdatePeminjamanView updateView = new UpdatePeminjamanView();

            new TransaksiPeminjamanController(transaksiPeminjamanView, tambahPeminjamanView, updateView, bukuRepo,
                    mahasiswaRepo, eksemplarBukuRepo,
                    transaksiPeminjamanRepo);

            BukuView bukuView = new BukuView();
            TambahBukuView tambahBukuView = new TambahBukuView();
            UpdateBukuView updateBukuView = new UpdateBukuView();

            new BukuController(bukuRepo, eksemplarBukuRepo, bukuView, tambahBukuView, updateBukuView);

            bukuView.setVisible(true);
        });
    }
}
