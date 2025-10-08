package com.manajemen.perpustakaan.repository;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.Buku;
import com.manajemen.perpustakaan.utils.JSONStorage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class BukuRepository {
    private final JSONStorage<Buku> storage;
    private Map<String, Buku> bukuMap;

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

    public Collection<Buku> getAll() {
        return this.bukuMap.values();
    }

    public Buku getByIsbn(String isbn) {
        return this.bukuMap.get(isbn);
    }
}
