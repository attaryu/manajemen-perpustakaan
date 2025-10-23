package com.manajemen.perpustakaan.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.entity.Mahasiswa;
import com.manajemen.perpustakaan.entity.TransaksiPeminjaman;
import com.manajemen.perpustakaan.entity.enumeration.StatusEksemplar;
import com.manajemen.perpustakaan.entity.enumeration.StatusPeminjaman;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;
import com.manajemen.perpustakaan.utils.DateUtils;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;
import com.manajemen.perpustakaan.view.UpdatePeminjamanView;
import com.manajemen.perpustakaan.view.column.action.ActionCallback;

/**
 * Controller untuk mengelola operasi CRUD transaksi peminjaman buku.
 * Menangani logika bisnis peminjaman, pengembalian, dan validasi status.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class TransaksiPeminjamanController {
    /** View untuk menampilkan daftar transaksi peminjaman */
    public final TransaksiPeminjamanView indexView;
    
    /** View untuk menambah transaksi peminjaman baru */
    public final TambahPeminjamanView addView;
    
    /** View untuk mengupdate transaksi peminjaman */
    public final UpdatePeminjamanView editView;

    /** Repository untuk operasi data buku */
    private final BukuRepository bukuRepo;
    
    /** Repository untuk operasi data mahasiswa */
    private final MahasiswaRepository mahasiswaRepo;
    
    /** Repository untuk operasi data eksemplar buku */
    private final EksemplarBukuRepository eksemplarBukuRepo;
    
    /** Repository untuk operasi data transaksi peminjaman */
    private final TransaksiPeminjamanRepository transaksiPeminjamanRepo;

    /** Callback untuk navigasi ke halaman utama */
    private Runnable navigateToMain;

    /**
     * Konstruktor untuk TransaksiPeminjamanController.
     * 
     * @param indexView view daftar transaksi
     * @param addView view tambah transaksi
     * @param editView view update transaksi
     * @param bukuRepo repository buku
     * @param mahasiswaRepo repository mahasiswa
     * @param eksemplarBukuRepo repository eksemplar buku
     * @param transaksiPeminjamanRepo repository transaksi peminjaman
     * @param navigateToMain callback navigasi ke main
     */
    public TransaksiPeminjamanController(TransaksiPeminjamanView indexView, TambahPeminjamanView addView,
            UpdatePeminjamanView editView, BukuRepository bukuRepo,
            MahasiswaRepository mahasiswaRepo, EksemplarBukuRepository eksemplarBukuRepo,
            TransaksiPeminjamanRepository transaksiPeminjamanRepo, Runnable navigateToMain) {
        this.bukuRepo = bukuRepo;
        this.mahasiswaRepo = mahasiswaRepo;
        this.eksemplarBukuRepo = eksemplarBukuRepo;
        this.transaksiPeminjamanRepo = transaksiPeminjamanRepo;

        this.indexView = indexView;
        this.addView = addView;
        this.editView = editView;

        this.navigateToMain = navigateToMain;
    }

    // main method

    /**
     * Menampilkan halaman index/daftar transaksi peminjaman.
     * Setup event listener untuk pencarian, tambah, edit, dan delete.
     */
    public void index() {
        this.refreshData();

        for (java.awt.event.ActionListener listener : this.indexView.getSearchBar().getActionListeners()) {
            this.indexView.getSearchBar().removeActionListener(listener);
        }

        this.indexView.getSearchBar().addActionListener((_) -> this.refreshData());

        for (java.awt.event.ActionListener listener : this.indexView.getTambahButton().getActionListeners()) {
            this.indexView.getTambahButton().removeActionListener(listener);
        }

        this.indexView.getTambahButton().addActionListener((_) -> this.create());

        for (java.awt.event.ActionListener listener : this.addView.getSubmitButton().getActionListeners()) {
            this.addView.getSubmitButton().removeActionListener(listener);
        }

        this.addView.getSubmitButton().addActionListener((_) -> this.store());

        this.indexView.setActionCallback(new ActionCallback() {
            @Override
            public void onEdit(String id) {
                TransaksiPeminjamanController.this.edit(id);
            }

            @Override
            public void onDelete(String id) {
                TransaksiPeminjamanController.this.destroy(id);
            }
        });

        for (java.awt.event.WindowListener listener : this.indexView.getWindowListeners()) {
            this.indexView.removeWindowListener(listener);
        }

        this.indexView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                TransaksiPeminjamanController.this.navigateToMain.run();
            }
        });

        this.indexView.setVisible(true);
    }

    /**
     * Menampilkan form untuk menambah transaksi peminjaman baru.
     * Setup dropdown buku dan eksemplar dengan validasi ketersediaan.
     */
    private void create() {
        try {
            this.addView.setLocationRelativeTo(null);
            this.addView.setVisible(true);

            javax.swing.JComboBox<String> bukuDropdown = this.addView.getBukuDropdown();

            for (java.awt.event.ActionListener listener : bukuDropdown.getActionListeners()) {
                bukuDropdown.removeActionListener(listener);
            }

            bukuDropdown.removeAllItems();
            bukuDropdown.addItem("Pilih buku");

            List<String> bukuOptions = this.bukuRepo
                    .getAll()
                    .stream()
                    .filter((buku) -> !this.eksemplarBukuRepo
                            .getByIsbn(buku.getIsbn())
                            .stream()
                            .filter(EksemplarBuku::isAvailable)
                            .toList()
                            .isEmpty())
                    .map((buku) -> String.format("%s - %s", buku.getJudul(), buku.getIsbn()))
                    .toList();

            bukuOptions.forEach(bukuDropdown::addItem);

            javax.swing.JComboBox<String> eksemplarDropdown = this.addView.getEksemplarDropdown();
            eksemplarDropdown.removeAllItems();
            eksemplarDropdown.addItem("Pilih buku terlebih dahulu");

            bukuDropdown.addActionListener((_) -> {
                String selected = bukuDropdown.getSelectedItem().toString();

                if (!selected.equals("Pilih buku")) {
                    String isbnBukuTerpilih = selected
                            .split(" - ")[1]
                            .trim();

                    eksemplarDropdown.removeAllItems();

                    this.eksemplarBukuRepo
                            .getByIsbn(isbnBukuTerpilih)
                            .stream()
                            .filter(EksemplarBuku::isAvailable)
                            .forEach((eksemplar) -> eksemplarDropdown.addItem(eksemplar.getNomorEksemplar()));
                }
            });

            for (java.awt.event.WindowListener listener : this.addView.getWindowListeners()) {
                this.addView.removeWindowListener(listener);
            }

            this.addView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    TransaksiPeminjamanController.this.addView.resetForm();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.indexView,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Menyimpan transaksi peminjaman baru.
     * Melakukan validasi dan membuat data mahasiswa baru jika belum terdaftar.
     */
    private void store() {
        try {
            Map<String, String> formData = this.addView.getFormData();

            String nrp = formData.get("nrp");
            String tanggalJatuhTempo = formData.get("tanggalJatuhTempo");
            String nomorEksemplar = formData.get("eksemplar");

            if (nrp.isEmpty() || tanggalJatuhTempo.isEmpty() || nomorEksemplar.equals("Pilih buku terlebih dahulu")) {
                throw new Exception("NRP, nomor eksemplar, serta tanggal jatuh tempo wajib diisi.");
            }

            Mahasiswa mahasiswa = this.mahasiswaRepo.getByNrp(nrp);

            if (mahasiswa == null) {
                String nama = formData.get("nama");
                String prodi = formData.get("prodi");

                if (nama.isEmpty() || prodi.isEmpty()) {
                    throw new Exception(
                            "Mahasiswa dengan NRP tersebut tidak ditemukan, mohon lengkapi nama serta prodinya");
                }

                mahasiswa = Mahasiswa.create(nrp, nama, prodi);
                this.mahasiswaRepo.add(mahasiswa);
            }

            EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(nomorEksemplar);

            Date tanggalJatuhTempoDate = DateUtils.toDate(tanggalJatuhTempo);

            TransaksiPeminjaman newTransaksi = TransaksiPeminjaman.create(
                    mahasiswa,
                    eksemplar,
                    tanggalJatuhTempoDate);

            this.eksemplarBukuRepo.update(newTransaksi.getEksemplarBuku());
            this.transaksiPeminjamanRepo.add(newTransaksi);

            this.addView.dispose();
            this.refreshData();

            JOptionPane.showMessageDialog(this.indexView,
                    "Peminjaman berhasil ditambahkan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.addView,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Menampilkan form edit untuk transaksi peminjaman tertentu.
     * 
     * @param id ID transaksi yang akan diedit
     */
    private void edit(String id) {
        this.editView.setVisible(true);
        this.editView.resetForm();

        javax.swing.JButton submitButton = this.editView.getSubmitButton();

        for (java.awt.event.ActionListener listener : submitButton.getActionListeners()) {
            submitButton.removeActionListener(listener);
        }

        try {
            TransaksiPeminjaman transaksi = this.transaksiPeminjamanRepo.getById(id);

            if (transaksi == null) {
                throw new Exception("Data peminjaman tidak ditemukan.");
            }

            Mahasiswa peminjam = this.mahasiswaRepo.getByNrp(transaksi.getNrpPeminjam());
            EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(transaksi.getNomorEksemplar());
            Buku buku = this.bukuRepo.getByIsbn(eksemplar.getIsbn());

            transaksi.setPeminjam(peminjam);
            transaksi.setEksemplarBuku(eksemplar);

            Map<String, String> formData = Map.of(
                    "nama", peminjam.getNama(),
                    "nrp", peminjam.getNrp(),
                    "prodi", peminjam.getProdi(),
                    "buku", String.format("%s - %s", buku.getJudul(), buku.getIsbn()),
                    "tanggalPinjam", DateUtils.toString(transaksi.getTanggalPinjam()),
                    "tanggalJatuhTempo", DateUtils.toString(transaksi.getTanggalJatuhTempo()),
                    "tanggalKembali", DateUtils.toString(transaksi.getTanggalKembali()),
                    "status", transaksi.getStatus().toString());

            this.editView.setData(formData);

            submitButton.addActionListener((_) -> this.update(transaksi));

            for (java.awt.event.WindowListener listener : this.editView.getWindowListeners()) {
                this.editView.removeWindowListener(listener);
            }

            this.editView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    TransaksiPeminjamanController.this.editView.resetForm();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.indexView,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mengupdate data transaksi peminjaman.
     * 
     * @param transaksi object transaksi yang akan diupdate
     */
    private void update(TransaksiPeminjaman transaksi) {
        try {
            Map<String, String> formData = this.editView.getFormData();

            transaksi.setStatus(StatusPeminjaman.getStatus(formData.get("status")));
            transaksi.setTanggalKembali(DateUtils.toDate(formData.get("tanggalKembali")));

            transaksi.setTanggalPinjam(DateUtils.toDate(formData.get("tanggalPinjam")));
            transaksi.setTanggalJatuhTempo(DateUtils.toDate(formData.get("tanggalJatuhTempo")));

            this.eksemplarBukuRepo.update(transaksi.getEksemplarBuku());
            this.transaksiPeminjamanRepo.update(transaksi);

            this.editView.dispose();
            this.refreshData();

            JOptionPane.showMessageDialog(this.indexView,
                    "Data peminjaman berhasil diperbarui.",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.editView,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Menghapus transaksi peminjaman dan mengembalikan status eksemplar.
     * 
     * @param id ID transaksi yang akan dihapus
     */
    private void destroy(String id) {
        int deleteConfirm = JOptionPane.showConfirmDialog(this.indexView,
                "Apakah Anda yakin ingin menghapus data peminjaman ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (deleteConfirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            TransaksiPeminjaman transaksi = this.transaksiPeminjamanRepo.getById(id);

            if (transaksi == null) {
                throw new Exception("Data peminjaman tidak ditemukan.");
            }

            EksemplarBuku eksemplarBuku = this.eksemplarBukuRepo.getByNomorEksemplar(transaksi.getNomorEksemplar());
            eksemplarBuku.setStatus(StatusEksemplar.TERSEDIA);

            this.eksemplarBukuRepo.update(eksemplarBuku);
            this.transaksiPeminjamanRepo.delete(transaksi);
            this.refreshData();

            JOptionPane.showMessageDialog(this.indexView,
                    "Peminjaman berhasil dihapus.",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.indexView,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // utility

    /**
     * Mengkonversi data transaksi peminjaman menjadi format row tabel.
     * Data diurutkan berdasarkan prioritas status dan tanggal pinjam.
     * 
     * @param search keyword pencarian untuk filter data
     * @return list array object yang merepresentasikan row tabel
     */
    private List<Object[]> convertToTableRow(String search) {
        String processedSearch = search.trim().toLowerCase();

        return this.transaksiPeminjamanRepo
                .getAll()
                .stream()
                .sorted(Comparator.comparing(TransaksiPeminjaman::getTanggalPinjam).reversed())
                .sorted(Comparator.comparing(TransaksiPeminjaman::getStatus,
                        (status1, status2) -> Integer.compare(getStatusPriority(status1), getStatusPriority(status2))))
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
                            DateUtils.toString(transaksi.getTanggalPinjam()),
                            DateUtils.toString(transaksi.getTanggalJatuhTempo()),
                            null,
                            transaksi.getId(),
                    };
                })
                .filter((row) -> {
                    if (processedSearch.isBlank()) {
                        return true;
                    }

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

    /**
     * Mendapatkan prioritas sorting untuk status peminjaman.
     * 
     * @param status status peminjaman
     * @return nilai prioritas (semakin kecil semakin prioritas)
     */
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

    /**
     * Merefresh data tabel dengan data terbaru dari repository.
     */
    private void refreshData() {
        DefaultTableModel viewTabelModel = (DefaultTableModel) this.indexView.getTableModel();

        viewTabelModel.setRowCount(0);

        String currentSearch = this.indexView.getSearchBar().getText();
        List<Object[]> data = this.convertToTableRow(currentSearch);

        data.forEach(viewTabelModel::addRow);
    }
}
