package com.manajemen.perpustakaan.controller;

import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.entity.Mahasiswa;
import com.manajemen.perpustakaan.entity.TransaksiPeminjaman;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;

import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.List;

public class TransaksiPeminjamanController {
    public final TransaksiPeminjamanView view;

    private final BukuRepository bukuRepo;
    private final MahasiswaRepository mahasiswaRepo;
    private final EksemplarBukuRepository eksemplarBukuRepo;
    private final TransaksiPeminjamanRepository transaksiPeminjamanRepo;

    public TransaksiPeminjamanController(TransaksiPeminjamanView view) {
        this.view = view;

        this.bukuRepo = new BukuRepository();
        this.mahasiswaRepo = new MahasiswaRepository();
        this.eksemplarBukuRepo = new EksemplarBukuRepository();
        this.transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();

        this.init();
    }

    private void init() {
        List<Object[]> transaksiPeminjaman = this.transaksiPeminjamanRepo
                .getAll()
                .stream()
                .sorted(Comparator.comparing(TransaksiPeminjaman::getTanggalPinjam).reversed())
                .map((transaksi) -> {
                    Mahasiswa peminjam = this.mahasiswaRepo.getByNrp(transaksi.getNrpPeminjam());
                    EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(transaksi.getNomorEksemplar());
                    Buku buku = this.bukuRepo.getByIsbn(eksemplar.getIsbn());

                    return new Object[]{
                            peminjam.getNama(),
                            peminjam.getNrp(),
                            buku.getJudul(),
                            eksemplar.getNomorEksemplar(),
                            transaksi.getStatus(),
                            transaksi.getTanggalPinjam(),
                            transaksi.getTanggalJatuhTempo(),
                            null
                    };
                }).toList();

        DefaultTableModel viewTabelModel = (DefaultTableModel) this.view.getTable().getModel();
        transaksiPeminjaman.forEach(viewTabelModel::addRow);
        System.out.println("initialized");
    }
}
