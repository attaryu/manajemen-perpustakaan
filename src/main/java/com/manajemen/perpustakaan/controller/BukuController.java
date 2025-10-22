package com.manajemen.perpustakaan.controller;

import java.util.Comparator;
import java.util.List;

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
  private final BukuView indexView;
  private final TambahBukuView addView;
  private final UpdateBukuView editView;

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
        System.out.println("Delete action triggered for id: " + id);
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

    addButton.addActionListener((_) -> add());
  }

  public void add() {
    this.addView.setVisible(true);
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
