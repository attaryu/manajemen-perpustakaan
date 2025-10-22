package com.perpustakaan.controller;

import com.perpustakaan.model.Buku;
import com.perpustakaan.view.BukuView;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class BukuController {
    
    private BukuView view;
    // Nanti, Anda akan punya ArrayList untuk menyimpan data buku
    // private ArrayList<Buku> daftarBuku; 

    public BukuController(BukuView view) {
        this.view = view;
        // this.daftarBuku = new ArrayList<>(); // Inisialisasi
        
        // Di sinilah Anda menambahkan 'listener' ke tombol-tombol di view
        // Contoh:
        // this.view.getBtnTambahBukuBaru().addActionListener(e -> {
        //     tampilkanDialogTambahBuku();
        // });
        
        // this.view.getTblMasterBuku().getSelectionModel().addListSelectionListener(e -> {
        //     tampilkanDetailBuku();
        // });
    }

    // Method untuk memuat data awal (bisa data dummy dulu)
    public void loadInitialData() {
        // 1. Buat beberapa objek Buku dummy
        // 2. Ambil model tabel dari view.getTblMasterBuku()
        // 3. Tambahkan data buku ke model tabel
    }
    
    // Method-method lain
    // private void tampilkanDialogTambahBuku() { ... }
    // private void tampilkanDetailBuku() { ... }
    // private void loadEksemplarUntukBuku() { ... }
}