package com.manajemen.perpustakaan.repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.TransaksiPeminjaman;
import com.manajemen.perpustakaan.utils.JSONStorage;

/**
 * Repository untuk operasi CRUD data transaksi peminjaman.
 * Menggunakan HashMap untuk lookup cepat berdasarkan ID transaksi.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class TransaksiPeminjamanRepository {
    /** Storage JSON untuk persistence */
    private final JSONStorage<TransaksiPeminjaman> storage;
    
    /** Map transaksi peminjaman dengan ID sebagai key */
    private Map<String, TransaksiPeminjaman> transaksiPeminjamanMap;

    /**
     * Konstruktor yang menginisialisasi storage dan memuat data dari file JSON.
     */
    public TransaksiPeminjamanRepository() {
        Type listType = new TypeToken<ArrayList<TransaksiPeminjaman>>() {
        }.getType();

        this.storage = new JSONStorage<>("transaksi_peminjaman", listType);
        ArrayList<TransaksiPeminjaman> daftarTransaksiPeminjaman = this.storage.get();

        // Convert ArrayList to Map for faster lookup
        this.transaksiPeminjamanMap = new HashMap<>();
        for (TransaksiPeminjaman transaksi : daftarTransaksiPeminjaman) {
            this.transaksiPeminjamanMap.put(transaksi.getId(), transaksi);
        }
    }

    /**
     * Mendapatkan semua data transaksi peminjaman.
     * 
     * @return koleksi semua transaksi peminjaman
     */
    public Collection<TransaksiPeminjaman> getAll() {
        return this.transaksiPeminjamanMap.values();
    }

    /**
     * Mencari transaksi peminjaman berdasarkan ID.
     * 
     * @param id ID transaksi yang dicari
     * @return object transaksi peminjaman jika ditemukan, null jika tidak
     */
    public TransaksiPeminjaman getById(String id) {
        return this.transaksiPeminjamanMap.get(id);
    }

    /**
     * Menambahkan transaksi peminjaman baru dan menyimpan ke storage.
     * 
     * @param transaksiPeminjaman object transaksi peminjaman yang akan ditambahkan
     */
    public void add(TransaksiPeminjaman transaksiPeminjaman) {
        this.transaksiPeminjamanMap.put(transaksiPeminjaman.getId(), transaksiPeminjaman);
        this.storage.put(new ArrayList<>(this.transaksiPeminjamanMap.values()));
    }

    /**
     * Mengupdate data transaksi peminjaman dan menyimpan ke storage.
     * 
     * @param transaksiPeminjaman object transaksi peminjaman yang akan diupdate
     */
    public void update(TransaksiPeminjaman transaksiPeminjaman) {
        this.transaksiPeminjamanMap.put(transaksiPeminjaman.getId(), transaksiPeminjaman);
        this.storage.put(new ArrayList<>(this.transaksiPeminjamanMap.values()));
    }

    /**
     * Menghapus transaksi peminjaman dan menyimpan perubahan ke storage.
     * 
     * @param transaksiPeminjaman object transaksi peminjaman yang akan dihapus
     */
    public void delete(TransaksiPeminjaman transaksiPeminjaman) {
        this.transaksiPeminjamanMap.remove(transaksiPeminjaman.getId());
        this.storage.put(new ArrayList<>(this.transaksiPeminjamanMap.values()));
    }
}
