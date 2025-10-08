package com.manajemen.perpustakaan.repository;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.Mahasiswa;
import com.manajemen.perpustakaan.utils.JSONStorage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class MahasiswaRepository {
    private final JSONStorage<Mahasiswa> storage;
    private Map<String, Mahasiswa> mahasiswaMap;

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


    public Mahasiswa getByNrp(String nrp) {
        return this.mahasiswaMap.get(nrp);
    }
}
