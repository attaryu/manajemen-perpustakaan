package com.manajemen.perpustakaan.repository;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.EksemplarBuku;
import com.manajemen.perpustakaan.utils.JSONStorage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Repository untuk operasi CRUD data eksemplar buku.
 * Menggunakan HashMap untuk lookup cepat berdasarkan nomor eksemplar.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class EksemplarBukuRepository {
    /** Storage JSON untuk persistence */
    private final JSONStorage<EksemplarBuku> storage;
    
    /** Map eksemplar buku dengan nomor eksemplar sebagai key */
    private Map<String, EksemplarBuku> eksemplarBukuMap;

    /**
     * Konstruktor yang menginisialisasi storage dan memuat data dari file JSON.
     */
    public EksemplarBukuRepository() {
        Type listType = new TypeToken<ArrayList<EksemplarBuku>>() {
        }.getType();

        this.storage = new JSONStorage<>("eksemplar_buku", listType);
        ArrayList<EksemplarBuku> daftarEksemplarBuku = this.storage.get();

        // Convert ArrayList to Map for faster lookup
        this.eksemplarBukuMap = new HashMap<>();
        for (EksemplarBuku eksemplar : daftarEksemplarBuku) {
            this.eksemplarBukuMap.put(eksemplar.getNomorEksemplar(), eksemplar);
        }
    }

    /**
     * Mendapatkan semua data eksemplar buku.
     * 
     * @return koleksi semua eksemplar buku
     */
    public Collection<EksemplarBuku> getAll() {
        return this.eksemplarBukuMap.values();
    }

    /**
     * Mencari eksemplar buku berdasarkan nomor eksemplar.
     * 
     * @param nomorEksemplar nomor eksemplar yang dicari
     * @return object eksemplar buku jika ditemukan, null jika tidak
     */
    public EksemplarBuku getByNomorEksemplar(String nomorEksemplar) {
        return this.eksemplarBukuMap.get(nomorEksemplar);
    }

    /**
     * Mendapatkan semua eksemplar buku berdasarkan ISBN.
     * 
     * @param isbn ISBN buku yang eksemplarnya dicari
     * @return koleksi eksemplar buku dengan ISBN tertentu
     */
    public Collection<EksemplarBuku> getByIsbn(String isbn) {
        return this.eksemplarBukuMap.values().stream().filter(eksemplar -> eksemplar.getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }

    /**
     * Menambahkan banyak eksemplar sekaligus dan menyimpan ke storage.
     * 
     * @param eksemplars koleksi eksemplar buku yang akan ditambahkan
     */
    public void putMany(Collection<EksemplarBuku> eksemplars) {
        for (EksemplarBuku eksemplar : eksemplars) {
            this.eksemplarBukuMap.put(eksemplar.getNomorEksemplar(), eksemplar);
        }

        this.storage.put(new ArrayList<>(this.eksemplarBukuMap.values()));
    }

    /**
     * Mengupdate data eksemplar buku dan menyimpan ke storage.
     * 
     * @param eksemplarBuku object eksemplar buku yang akan diupdate
     */
    public void update(EksemplarBuku eksemplarBuku) {
        this.eksemplarBukuMap.put(eksemplarBuku.getNomorEksemplar(), eksemplarBuku);
        this.storage.put(new ArrayList<>(this.eksemplarBukuMap.values()));
    }

    /**
     * Menghapus eksemplar buku dan menyimpan perubahan ke storage.
     * 
     * @param eksemplarBuku object eksemplar buku yang akan dihapus
     */
    public void delete(EksemplarBuku eksemplarBuku) {
        this.eksemplarBukuMap.remove(eksemplarBuku.getNomorEksemplar());
        this.storage.put(new ArrayList<>(this.eksemplarBukuMap.values()));
    }
}
