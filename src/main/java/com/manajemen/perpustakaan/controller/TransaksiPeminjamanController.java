package com.manajemen.perpustakaan.controller;

import java.time.LocalDate;
import java.util.Comparator;
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
import com.manajemen.perpustakaan.view.ActionCallback;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;

public class TransaksiPeminjamanController {
    public final TransaksiPeminjamanView indexView;
    public final TambahPeminjamanView addView;

    private final BukuRepository bukuRepo;
    private final MahasiswaRepository mahasiswaRepo;
    private final EksemplarBukuRepository eksemplarBukuRepo;
    private final TransaksiPeminjamanRepository transaksiPeminjamanRepo;

    public TransaksiPeminjamanController(TransaksiPeminjamanView indexView, TambahPeminjamanView addView) {
        this.bukuRepo = new BukuRepository();
        this.mahasiswaRepo = new MahasiswaRepository();
        this.eksemplarBukuRepo = new EksemplarBukuRepository();
        this.transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();

        this.indexView = indexView;
        this.addView = addView;

        this.index(this.convertToTableRow(""));

        this.indexView.getSearchBar().addActionListener((_) -> this.refreshData());

        this.indexView.getTambahButton().addActionListener((_) -> this.create());
        this.addView.getSubmitButton().addActionListener((_) -> this.store());

        this.indexView.setActionCallback(new ActionCallback() {
            @Override
            public void onEdit(String id) {
                System.out.println("Edit row data: " + id);
                // this.edit();
            }

            @Override
            public void onDelete(String id) {
                TransaksiPeminjamanController.this.destroy(id);
            }
        });
    }

    // main method

    private void index(List<Object[]> data) {
        DefaultTableModel viewTabelModel = (DefaultTableModel) this.indexView.getTableModel();
        viewTabelModel.setRowCount(0);
        data.forEach(viewTabelModel::addRow);
    }

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
                            .forEach((eksemplar) -> eksemplarDropdown.addItem(eksemplar.getNomorEksemplar()));
                }
            });

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
            eksemplar.setStatus(StatusEksemplar.DIPINJAM);

            TransaksiPeminjaman newTransaksi = TransaksiPeminjaman.create(
                    mahasiswa,
                    eksemplar,
                    LocalDate.parse(tanggalJatuhTempo));

            this.eksemplarBukuRepo.update(eksemplar);
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
                            transaksi.getTanggalPinjam(),
                            transaksi.getTanggalJatuhTempo(),
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

    private void refreshData() {
        String currentSearch = this.indexView.getSearchBar().getText();
        this.index(this.convertToTableRow(currentSearch));
    }
}
