package com.manajemen.perpustakaan.repository;

import com.google.gson.reflect.TypeToken;
import com.manajemen.perpustakaan.entity.TransaksiPeminjaman;
import com.manajemen.perpustakaan.utils.JSONStorage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

public class TransaksiPeminjamanRepository {
    private final JSONStorage<TransaksiPeminjaman> storage;
    private Map<String, TransaksiPeminjaman> transaksiPeminjamanMap;

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

    public Collection<TransaksiPeminjaman> getAll() {
        return this.transaksiPeminjamanMap.values();
    }
    
    public TransaksiPeminjaman getById(String id) {
        return this.transaksiPeminjamanMap.get(id);
    }
    
    public Collection<TransaksiPeminjaman> getByNrpPeminjam(String nrpPeminjam) {
        return this.transaksiPeminjamanMap.values().stream()
                .filter(transaksi -> transaksi.getNrpPeminjam().equals(nrpPeminjam))
                .collect(Collectors.toList());
    }
    
    public Collection<TransaksiPeminjaman> getByNomorEksemplar(String nomorEksemplar) {
        return this.transaksiPeminjamanMap.values().stream()
                .filter(transaksi -> transaksi.getNomorEksemplar().equals(nomorEksemplar))
                .collect(Collectors.toList());
    }
    
    public void add(TransaksiPeminjaman transaksiPeminjaman) {
        this.transaksiPeminjamanMap.put(transaksiPeminjaman.getId(), transaksiPeminjaman);
        this.storage.put(new ArrayList<>(this.transaksiPeminjamanMap.values()));
    }
    
    public boolean remove(String id) {
        TransaksiPeminjaman removed = this.transaksiPeminjamanMap.remove(id);
        if (removed != null) {
            this.storage.put(new ArrayList<>(this.transaksiPeminjamanMap.values()));
            return true;
        }
        return false;
    }
}
