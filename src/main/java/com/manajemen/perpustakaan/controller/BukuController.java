package com.manajemen.perpustakaan.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.entity.enumeration.StatusEksemplar;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.view.BukuView;
import com.manajemen.perpustakaan.view.TambahBukuView;
import com.manajemen.perpustakaan.view.UpdateBukuView;
import com.manajemen.perpustakaan.view.column.action.ActionCallback;

/**
 * Controller untuk mengelola operasi CRUD buku dan koordinasi antar view buku.
 * Menangani logika bisnis untuk manajemen data buku, validasi, dan navigasi.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class BukuController {
  /** View untuk menampilkan daftar buku */
  public final BukuView indexView;
  
  /** View untuk menambah buku baru */
  public final TambahBukuView addView;
  
  /** View untuk mengupdate data buku */
  public final UpdateBukuView editView;

  /** Repository untuk operasi data buku */
  private final BukuRepository bukuRepo;
  
  /** Repository untuk operasi data eksemplar buku */
  private final EksemplarBukuRepository eksemplarBukuRepo;

  /** Callback untuk navigasi ke halaman utama */
  private Runnable navigateToMain;
  
  /** Callback untuk navigasi ke halaman eksemplar dengan parameter ISBN */
  private Consumer<String> navigateToEksemplar;

  /**
   * Konstruktor untuk BukuController.
   * 
   * @param bukuRepo repository buku
   * @param eksemplarBukuRepo repository eksemplar buku
   * @param indexView view daftar buku
   * @param addView view tambah buku
   * @param editView view update buku
   * @param navigateToMain callback navigasi ke main
   * @param navigateToEksemplar callback navigasi ke eksemplar
   */
  public BukuController(BukuRepository bukuRepo, EksemplarBukuRepository eksemplarBukuRepo, BukuView indexView,
      TambahBukuView addView,
      UpdateBukuView editView, Runnable navigateToMain, Consumer<String> navigateToEksemplar) {
    this.bukuRepo = bukuRepo;
    this.eksemplarBukuRepo = eksemplarBukuRepo;
    this.indexView = indexView;
    this.addView = addView;
    this.editView = editView;
    this.navigateToMain = navigateToMain;
    this.navigateToEksemplar = navigateToEksemplar;
  }

  /**
   * Menampilkan halaman index/daftar buku.
   * Melakukan setup event listener untuk pencarian, tambah, edit, delete, dan view.
   */
  public void index() {
    this.refreshData();

    javax.swing.JTextField searchField = this.indexView.getSearchField();

    for (java.awt.event.ActionListener actionListener : searchField.getActionListeners()) {
      searchField.removeActionListener(actionListener);
    }

    searchField.addActionListener((_) -> refreshData());

    javax.swing.JButton addButton = this.indexView.getAddButton();

    for (java.awt.event.ActionListener actionListener : addButton.getActionListeners()) {
      addButton.removeActionListener(actionListener);
    }

    addButton.addActionListener((_) -> this.create());
    this.indexView.setActionCallback(new ActionCallback() {
      @Override
      public void onEdit(String id) {
        BukuController.this.edit(id);
      }

      @Override
      public void onDelete(String id) {
        BukuController.this.destroy(id);
      }

      @Override
      public void onView(String id) {
        BukuController.this.navigateToEksemplar.accept(id);
      }
    });

    for (java.awt.event.WindowListener windowListener : this.indexView.getWindowListeners()) {
      this.indexView.removeWindowListener(windowListener);
    }

    this.indexView.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosed(java.awt.event.WindowEvent e) {
        BukuController.this.navigateToMain.run();
      }
    });

    this.indexView.setVisible(true);
  }

  /**
   * Menampilkan form untuk menambah buku baru.
   * Setup event listener untuk submit dan window close.
   */
  public void create() {
    this.addView.setVisible(true);

    javax.swing.JButton submitButton = this.addView.getSubmitButton();

    for (java.awt.event.ActionListener actionListener : submitButton.getActionListeners()) {
      submitButton.removeActionListener(actionListener);
    }

    submitButton.addActionListener((_) -> this.store());

    for (java.awt.event.WindowListener windowListener : this.addView.getWindowListeners()) {
      this.addView.removeWindowListener(windowListener);
    }

    this.addView.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosed(java.awt.event.WindowEvent e) {
        BukuController.this.addView.resetForm();
      }
    });
  }

  /**
   * Menyimpan data buku baru ke repository.
   * Melakukan validasi input dan pengecekan duplikasi ISBN.
   */
  private void store() {
    try {
      Map<String, String> formData = this.addView.getFormData();

      String isbn = formData.get("isbn");
      String judul = formData.get("judul");
      String penulis = formData.get("penulis");
      String penerbit = formData.get("penerbit");

      Buku existingBuku = this.bukuRepo.getByIsbn(isbn);

      if (judul.isBlank() || isbn.isBlank() || penulis.isBlank() || penerbit.isBlank()) {
        throw new Exception("Semua field harus diisi.");
      }

      if (existingBuku != null) {
        throw new Exception("ISBN sudah terdaftar.");
      }

      Buku newBuku = new Buku(
          formData.get("judul"),
          formData.get("isbn"),
          formData.get("penulis"),
          formData.get("penerbit"),
          Integer.parseInt(formData.get("jumlahHalaman")));

      this.bukuRepo.add(newBuku);
      this.addView.dispose();
      this.refreshData();

      JOptionPane.showMessageDialog(
          this.addView,
          "Buku berhasil ditambahkan!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this.addView,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Menampilkan form edit untuk buku dengan ISBN tertentu.
   * 
   * @param id ISBN buku yang akan diedit
   */
  private void edit(String id) {
    try {
      Buku buku = this.bukuRepo.getByIsbn(id);

      if (buku == null) {
        throw new Exception("Buku tidak ditemukan");
      }

      Map<String, String> data = Map.of(
          "isbn", buku.getIsbn(),
          "judul", buku.getJudul(),
          "penulis", buku.getPenulis(),
          "penerbit", buku.getPenerbit(),
          "jumlahHalaman", String.valueOf(buku.getJumlahHalaman()));

      this.editView.setForm(data);
      this.editView.setVisible(true);

      javax.swing.JButton submitButton = this.editView.getSubmitButton();

      for (java.awt.event.ActionListener actionListener : submitButton.getActionListeners()) {
        submitButton.removeActionListener(actionListener);
      }

      submitButton.addActionListener((_) -> this.update(buku));

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          this.indexView,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Mengupdate data buku yang sudah ada.
   * 
   * @param buku object buku yang akan diupdate
   */
  public void update(Buku buku) {
    try {
      Map<String, String> formData = this.editView.getForm();

      String judul = formData.get("judul");
      String penulis = formData.get("penulis");
      String penerbit = formData.get("penerbit");
      String jumlahHalaman = formData.get("jumlahHalaman");

      if (judul.isBlank() || penulis.isBlank() || penerbit.isBlank() || jumlahHalaman.isBlank()) {
        throw new Exception("Semua field harus diisi.");
      }

      buku.setJudul(judul);
      buku.setPenulis(penulis);
      buku.setPenerbit(penerbit);
      buku.setJumlahHalaman(Integer.parseInt(jumlahHalaman));

      this.bukuRepo.update(buku);

      this.editView.dispose();
      this.refreshData();

      JOptionPane.showMessageDialog(
          this.editView,
          "Buku berhasil diperbarui!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          this.indexView,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Menghapus buku dari repository.
   * Melakukan validasi bahwa tidak ada eksemplar yang sedang dipinjam.
   * 
   * @param id ISBN buku yang akan dihapus
   */
  private void destroy(String id) {
    int confirmation = JOptionPane.showConfirmDialog(
        this.indexView,
        "Apakah Anda yakin ingin menghapus buku ini?",
        "Konfirmasi Hapus",
        JOptionPane.YES_NO_OPTION);

    if (confirmation != JOptionPane.YES_OPTION) {
      return;
    }

    try {
      Buku buku = this.bukuRepo.getByIsbn(id);

      if (buku == null) {
        throw new Exception("Buku tidak ditemukan.");
      }

      List<EksemplarBuku> eksemplars = this.eksemplarBukuRepo
          .getByIsbn(buku.getIsbn())
          .stream()
          .filter((eksemplar) -> eksemplar.getStatus().equals(StatusEksemplar.DIPINJAM))
          .toList();

      if (!eksemplars.isEmpty()) {
        throw new Exception("Buku tidak dapat dihapus karena ada eksemplar yang sedang dipinjam.");
      }

      this.bukuRepo.delete(buku);
      this.refreshData();

      JOptionPane.showMessageDialog(
          this.indexView,
          "Buku berhasil dihapus!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this.indexView,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  // utils

  /**
   * Mengkonversi data buku menjadi format row tabel dengan fitur pencarian.
   * 
   * @param search keyword pencarian untuk filter data
   * @return list array object yang merepresentasikan row tabel
   */
  private List<Object[]> convertToTableRow(String search) {
    String processedSearch = search.trim().toLowerCase();

    return this.bukuRepo
        .getAll()
        .stream()
        .sorted(Comparator.comparing(Buku::getJudul))
        .map((buku) -> {
          List<EksemplarBuku> eksemplars = this.eksemplarBukuRepo.getByIsbn(buku.getIsbn()).stream().toList();

          int eksemplarTersedia = eksemplars.stream()
              .filter((eksemplar) -> eksemplar.getStatus().equals(StatusEksemplar.TERSEDIA))
              .toList().size();

          int eksemplarTerpinjam = eksemplars.stream()
              .filter((eksemplar) -> eksemplar.getStatus().equals(StatusEksemplar.DIPINJAM))
              .toList().size();

          return new Object[] {
              buku.getJudul(),
              buku.getIsbn(),
              buku.getPenulis(),
              buku.getPenerbit(),
              eksemplarTerpinjam,
              eksemplarTersedia,
              eksemplarTerpinjam + eksemplarTersedia,
              null
          };
        })
        .filter((row) -> {
          if (processedSearch.isBlank()) {
            return true;
          }

          String judul = row[0].toString().toLowerCase();
          String isbn = row[1].toString().toLowerCase();
          String penulis = row[2].toString().toLowerCase();
          String penerbit = row[3].toString().toLowerCase();

          return judul.contains(processedSearch)
              || isbn.contains(processedSearch)
              || penulis.contains(processedSearch)
              || penerbit.contains(processedSearch);
        })
        .toList();
  }

  /**
   * Merefresh data tabel dengan data terbaru dari repository.
   */
  private void refreshData() {
    String searchField = this.indexView.getSearchField().getText();
    this.indexView.setTableData(this.convertToTableRow(searchField));
  }
}
