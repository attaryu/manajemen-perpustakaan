package com.manajemen.perpustakaan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.view.EksemplarView;
import com.manajemen.perpustakaan.view.column.action.ActionCallback;

/**
 * Controller untuk mengelola eksemplar/copy buku.
 * Menangani operasi tambah dan hapus eksemplar buku tertentu.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class EksemplarController {
  /** View untuk menampilkan daftar eksemplar */
  private EksemplarView view;

  /** Repository untuk operasi data eksemplar buku */
  private EksemplarBukuRepository eksemplarBukuRepo;
  
  /** Repository untuk operasi data buku */
  private BukuRepository bukuRepo;

  /** Callback untuk navigasi kembali ke halaman buku */
  private Runnable navigateToBuku;

  /**
   * Konstruktor untuk EksemplarController.
   * 
   * @param view view eksemplar
   * @param bukuRepo repository buku
   * @param eksemplarBukuRepo repository eksemplar buku
   * @param navigateToBuku callback navigasi ke buku
   */
  public EksemplarController(EksemplarView view, BukuRepository bukuRepo, EksemplarBukuRepository eksemplarBukuRepo,
      Runnable navigateToBuku) {
    this.view = view;
    this.eksemplarBukuRepo = eksemplarBukuRepo;
    this.bukuRepo = bukuRepo;
    this.navigateToBuku = navigateToBuku;
  }

  /**
   * Menampilkan daftar eksemplar untuk buku tertentu.
   * 
   * @param isbn ISBN buku yang eksemplarnya akan ditampilkan
   */
  public void index(String isbn) {
    this.view.setVisible(true);

    try {
      Buku buku = this.bukuRepo.getByIsbn(isbn);

      if (buku == null) {
        throw new Exception("Buku tidak ditemukan.");
      }

      this.refreshData(buku);

      for (java.awt.event.WindowListener wl : this.view.getWindowListeners()) {
        this.view.removeWindowListener(wl);
      }

      this.view.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
          EksemplarController.this.navigateToBuku.run();
        }
      });

      javax.swing.JButton addEksemplarButton = this.view.getAddEksemplarButton();

      for (java.awt.event.ActionListener al : addEksemplarButton.getActionListeners()) {
        addEksemplarButton.removeActionListener(al);
      }

      addEksemplarButton.addActionListener((_) -> this.store(buku));

      this.view.setActionCallback(new ActionCallback() {
        @Override
        public void onDelete(String nomorEksemplar) {
          EksemplarController.this.destroy(nomorEksemplar, buku);
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          this.view,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);

      this.view.dispose();
    }
  }

  /**
   * Menambahkan eksemplar baru untuk buku tertentu.
   * Meminta input jumlah eksemplar yang akan ditambahkan.
   * 
   * @param buku object buku yang akan ditambahkan eksemplarnya
   */
  public void store(Buku buku) {
    String totalInString = JOptionPane.showInputDialog(
        this.view,
        "Masukkan jumlah eksemplar yang akan ditambahkan:",
        "Tambah Eksemplar",
        JOptionPane.PLAIN_MESSAGE);

    if (totalInString == null) {
      return;
    }

    try {
      int total = Integer.parseInt(totalInString);

      List<EksemplarBuku> newEksemplars = new ArrayList<>();

      for (int i = 0; i < total; i++) {
        EksemplarBuku eksemplar = EksemplarBuku.create(buku);
        newEksemplars.add(eksemplar);
      }

      this.eksemplarBukuRepo.putMany(newEksemplars);

      this.refreshData(buku);
    } catch (Exception e) {

    }
  }

  /**
   * Menghapus eksemplar buku.
   * Validasi bahwa eksemplar tidak sedang dipinjam.
   * 
   * @param nomorEksemplar nomor eksemplar yang akan dihapus
   * @param buku object buku pemilik eksemplar
   */
  public void destroy(String nomorEksemplar, Buku buku) {
    int confirm = JOptionPane.showConfirmDialog(
        this.view,
        "Apakah Anda yakin ingin menghapus eksemplar ini?",
        "Konfirmasi Hapus Eksemplar",
        JOptionPane.YES_NO_OPTION);

    if (confirm != JOptionPane.YES_OPTION) {
      return;
    }

    try {
      EksemplarBuku eksemplar = this.eksemplarBukuRepo.getByNomorEksemplar(nomorEksemplar);

      if (!eksemplar.isAvailable()) {
        throw new Exception("Eksemplar tidak dapat dihapus karena sedang dipinjam.");
      }

      this.eksemplarBukuRepo.delete(eksemplar);
      this.refreshData(buku);

      JOptionPane.showMessageDialog(
          this.view,
          "Eksemplar berhasil dihapus.",
          "Sukses",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          this.view,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Merefresh data tabel eksemplar dengan data terbaru.
   * 
   * @param buku object buku yang eksemplarnya akan ditampilkan
   */
  private void refreshData(Buku buku) {
    List<Object[]> eksemplarList = this.eksemplarBukuRepo.getByIsbn(buku.getIsbn())
        .stream()
        .map((eksemplar) -> {
          return new Object[] {
              eksemplar.getNomorEksemplar(),
              eksemplar.getStatus(),
              null
          };
        }).toList();

    this.view.setTableData(eksemplarList);
  }
}
