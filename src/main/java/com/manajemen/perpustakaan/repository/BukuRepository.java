package com.manajemen.perpustakaan.repository;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.utils.JSONStorage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Repository untuk operasi CRUD data buku.
 * Menggunakan HashMap untuk lookup cepat berdasarkan ISBN.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class BukuRepository {
    /** Storage JSON untuk persistence */
    private final JSONStorage<Buku> storage;
    
    /** Map buku dengan ISBN sebagai key untuk lookup cepat */
    private Map<String, Buku> bukuMap;

    /**
     * Konstruktor yang menginisialisasi storage dan memuat data dari file JSON.
     */
    public BukuRepository() {
        Type listType = new TypeToken<ArrayList<Buku>>() {
        }.getType();

        this.storage = new JSONStorage<>("buku", listType);
        ArrayList<Buku> daftarBuku = this.storage.get();

        // Convert ArrayList to Map for faster lookup
        this.bukuMap = new HashMap<>();
        for (Buku buku : daftarBuku) {
            this.bukuMap.put(buku.getIsbn(), buku);
        }
    }

    /**
     * Mendapatkan semua data buku.
     * 
     * @return koleksi semua buku
     */
    public Collection<Buku> getAll() {
        return this.bukuMap.values();
    }

    /**
     * Mencari buku berdasarkan ISBN.
     * 
     * @param isbn ISBN buku yang dicari
     * @return object buku jika ditemukan, null jika tidak
     */
    public Buku getByIsbn(String isbn) {
        return this.bukuMap.get(isbn);
    }

    /**
     * Menambahkan buku baru ke repository dan menyimpan ke storage.
     * 
     * @param buku object buku yang akan ditambahkan
     */
    public void add(Buku buku) {
        this.bukuMap.put(buku.getIsbn(), buku);
        this.storage.put(new ArrayList<>(this.bukuMap.values()));
    }

    /**
     * Mengupdate data buku yang sudah ada dan menyimpan ke storage.
     * 
     * @param buku object buku yang akan diupdate
     */
    public void update(Buku buku) {
        this.bukuMap.put(buku.getIsbn(), buku);
        this.storage.put(new ArrayList<>(this.bukuMap.values()));
    }
    
    /**
     * Menghapus buku dari repository dan menyimpan perubahan ke storage.
     * 
     * @param buku object buku yang akan dihapus
     */
    public void delete(Buku buku) {
        this.bukuMap.remove(buku.getIsbn());
        this.storage.put(new ArrayList<>(this.bukuMap.values()));
    }
}
