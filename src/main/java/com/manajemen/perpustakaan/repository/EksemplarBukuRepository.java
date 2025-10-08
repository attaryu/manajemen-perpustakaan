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

public class EksemplarBukuRepository {
    private final JSONStorage<EksemplarBuku> storage;
    private Map<String, EksemplarBuku> eksemplarBukuMap;

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

    public Collection<EksemplarBuku> getAll() {
        return this.eksemplarBukuMap.values();
    }

    public EksemplarBuku getByNomorEksemplar(String nomorEksemplar) {
        return this.eksemplarBukuMap.get(nomorEksemplar);
    }

    public Collection<EksemplarBuku> getByIsbn(String isbn) {
        return this.eksemplarBukuMap.values().stream().filter(eksemplar -> eksemplar.getIsbn().equals(isbn)).collect(Collectors.toList());
    }
}
