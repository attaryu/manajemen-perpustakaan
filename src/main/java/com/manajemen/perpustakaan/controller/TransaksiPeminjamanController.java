package com.manajemen.perpustakaan.controller;

import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.entity.Mahasiswa;
import com.manajemen.perpustakaan.entity.TransaksiPeminjaman;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;

import java.util.Collection;

public class TransaksiPeminjamanController {
    private final BukuRepository bukuRepo;
    private final MahasiswaRepository mahasiswaRepo;
    private final EksemplarBukuRepository eksemplarBukuRepo;
    private final TransaksiPeminjamanRepository transaksiPeminjamanRepo;

    public TransaksiPeminjamanController() {
        this.bukuRepo = new BukuRepository();
        this.mahasiswaRepo = new MahasiswaRepository();
        this.eksemplarBukuRepo = new EksemplarBukuRepository();
        this.transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();

        this.init();
    }

    private void init() {
        Collection<TransaksiPeminjaman> transaksiPeminjaman = this.transaksiPeminjamanRepo.getAll();

        transaksiPeminjaman.forEach((transaksi) -> {
            Mahasiswa peminjam = this.mahasiswaRepo.getByNrp(transaksi.getNrpPeminjam());
            EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(transaksi.getNomorEksemplar());
            Buku buku = this.bukuRepo.getByIsbn(eksemplar.getIsbn());

            eksemplar.setDetailBuku(buku);
            transaksi.setPeminjam(peminjam);
            transaksi.setEksemplarBuku(eksemplar);
        });

        transaksiPeminjaman.forEach((transaksi) -> {
            System.out.println("id: " + transaksi.getId());
            System.out.println("judul eksemplar: " + transaksi.getEksemplarBuku().getDetailBuku().getJudul());
            System.out.println("nama peminjam: " + transaksi.getPeminjam().getNama());
            System.out.println("=============================================");
        });
    }
}
