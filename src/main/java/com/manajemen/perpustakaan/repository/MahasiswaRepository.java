package com.manajemen.perpustakaan.repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.Mahasiswa;
import com.manajemen.perpustakaan.utils.JSONStorage;

/**
 * Repository untuk operasi data mahasiswa.
 * Menggunakan HashMap untuk lookup cepat berdasarkan NRP.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class MahasiswaRepository {
    /** Storage JSON untuk persistence */
    private final JSONStorage<Mahasiswa> storage;
    
    /** Map mahasiswa dengan NRP sebagai key untuk lookup cepat */
    private Map<String, Mahasiswa> mahasiswaMap;

    /**
     * Konstruktor yang menginisialisasi storage dan memuat data dari file JSON.
     */
    public MahasiswaRepository() {
        Type listType = new TypeToken<ArrayList<Mahasiswa>>() {
        }.getType();

        this.storage = new JSONStorage<>("mahasiswa", listType);
        ArrayList<Mahasiswa> daftarMahasiswa = this.storage.get();

        // Convert ArrayList to Map for faster lookup
        this.mahasiswaMap = new HashMap<>();

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            this.mahasiswaMap.put(mahasiswa.getNrp(), mahasiswa);
        }
    }

    /**
     * Mencari mahasiswa berdasarkan NRP.
     * 
     * @param nrp NRP mahasiswa yang dicari
     * @return object mahasiswa jika ditemukan, null jika tidak
     */
    public Mahasiswa getByNrp(String nrp) {
        return this.mahasiswaMap.get(nrp);
    }

    /**
     * Menambahkan mahasiswa baru ke repository dan menyimpan ke storage.
     * 
     * @param mahasiswa object mahasiswa yang akan ditambahkan
     */
    public void add(Mahasiswa mahasiswa) {
        this.mahasiswaMap.put(mahasiswa.getNrp(), mahasiswa);
        this.storage.put(new ArrayList<>(this.mahasiswaMap.values()));
    }
}
