package com.manajemen.perpustakaan.controller;

import java.util.Comparator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.entity.Mahasiswa;
import com.manajemen.perpustakaan.entity.TransaksiPeminjaman;
import com.manajemen.perpustakaan.entity.enumeration.StatusPeminjaman;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;

public class TransaksiPeminjamanController {
    public final TransaksiPeminjamanView view;

    private final BukuRepository bukuRepo;
    private final MahasiswaRepository mahasiswaRepo;
    private final EksemplarBukuRepository eksemplarBukuRepo;
    private final TransaksiPeminjamanRepository transaksiPeminjamanRepo;

    public TransaksiPeminjamanController(TransaksiPeminjamanView view) {
        this.bukuRepo = new BukuRepository();
        this.mahasiswaRepo = new MahasiswaRepository();
        this.eksemplarBukuRepo = new EksemplarBukuRepository();
        this.transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();

        this.view = view;

        // init data
        this.insertTabel(this.toRow());

        this.view.getSearchBar().addActionListener((_) -> {
            String search = this.view.getSearchBar().getText();

            if (search.isBlank()) {
                this.insertTabel(this.toRow());
                return;
            }

            this.insertTabel(this.toRow(search));
        });
    }

    private void insertTabel(List<Object[]> data) {
        DefaultTableModel viewTabelModel = (DefaultTableModel) this.view.getTableModel();
        viewTabelModel.setRowCount(0);
        data.forEach(viewTabelModel::addRow);
    }

    private List<Object[]> toRow() {
        return this.transaksiPeminjamanRepo
                .getAll()
                .stream()
                .sorted(Comparator.comparing(TransaksiPeminjaman::getTanggalPinjam).reversed())
                .sorted(Comparator.comparing(TransaksiPeminjaman::getStatus, (status1, status2) -> {
                    int priority1 = getStatusPriority(status1);
                    int priority2 = getStatusPriority(status2);
                    return Integer.compare(priority1, priority2);
                }))
                .map((transaksi) -> {
                    Mahasiswa peminjam = this.mahasiswaRepo.getByNrp(transaksi.getNrpPeminjam());
                    EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(transaksi.getNomorEksemplar());
                    Buku buku = this.bukuRepo.getByIsbn(eksemplar.getIsbn());

                    return new Object[] {
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
    }

    private List<Object[]> toRow(String search) {
        String processedSearch = search.trim().toLowerCase();

        return this.transaksiPeminjamanRepo
                .getAll()
                .stream()
                .sorted(Comparator.comparing(TransaksiPeminjaman::getTanggalPinjam).reversed())
                .sorted(Comparator.comparing(TransaksiPeminjaman::getStatus, (status1, status2) -> {
                    int priority1 = getStatusPriority(status1);
                    int priority2 = getStatusPriority(status2);
                    return Integer.compare(priority1, priority2);
                }))
                .map((transaksi) -> {
                    Mahasiswa peminjam = this.mahasiswaRepo.getByNrp(transaksi.getNrpPeminjam());
                    EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(transaksi.getNomorEksemplar());
                    Buku buku = this.bukuRepo.getByIsbn(eksemplar.getIsbn());

                    return new Object[] {
                            peminjam.getNama(),
                            peminjam.getNrp(),
                            buku.getJudul(),
                            eksemplar.getNomorEksemplar(),
                            transaksi.getStatus(),
                            transaksi.getTanggalPinjam(),
                            transaksi.getTanggalJatuhTempo(),
                            null
                    };
                })
                .filter((row) -> {
                    String nama = row[0].toString().toLowerCase();
                    String nrp = row[1].toString().toLowerCase();
                    String judulBuku = row[2].toString().toLowerCase();
                    String nomorEksemplar = row[3].toString().toLowerCase();
                    String status = row[4].toString().toLowerCase();
                    String tanggalPinjam = row[5].toString().toLowerCase();
                    String tanggalJatuhTempo = row[6].toString().toLowerCase();

                    return nama.contains(processedSearch)
                            || nrp.contains(processedSearch)
                            || judulBuku.contains(processedSearch)
                            || nomorEksemplar.contains(processedSearch)
                            || status.contains(processedSearch)
                            || tanggalPinjam.contains(processedSearch)
                            || tanggalJatuhTempo.contains(processedSearch);
                })
                .toList();
    }

    private int getStatusPriority(StatusPeminjaman status) {
        switch (status) {
            case DIPINJAM:
                return 1;
            case HILANG:
                return 2;
            case TERLAMBAT:
                return 3;
            case SELESAI:
                return 4;
            default:
                return 999;
        }
    }
}
