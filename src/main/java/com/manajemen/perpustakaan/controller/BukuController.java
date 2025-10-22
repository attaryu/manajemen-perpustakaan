package com.manajemen.perpustakaan.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

public class BukuController {
  public final BukuView indexView;
  public final TambahBukuView addView;
  public final UpdateBukuView editView;

  private final BukuRepository bukuRepo;
  private final EksemplarBukuRepository eksemplarBukuRepo;

  public BukuController(BukuRepository bukuRepo, EksemplarBukuRepository eksemplarBukuRepo, BukuView indexView,
      TambahBukuView addView,
      UpdateBukuView editView) {
    this.bukuRepo = bukuRepo;
    this.eksemplarBukuRepo = eksemplarBukuRepo;
    this.indexView = indexView;
    this.addView = addView;
    this.editView = editView;

    this.index();
  }

  public void index() {
    this.indexView.setActionCallback(new ActionCallback() {
      @Override
      public void onEdit(String id) {
        System.out.println("Edit action triggered for id: " + id);
      }

      @Override
      public void onDelete(String id) {
        BukuController.this.destroy(id);
      }
    });

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
  }

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

  private void refreshData() {
    String searchField = this.indexView.getSearchField().getText();
    this.indexView.setTableData(this.convertToTableRow(searchField));
  }
}
