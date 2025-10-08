package com.manajemen.perpustakaan.entity.enumeration;

public enum StatusPeminjaman {
    DIPINJAM("Dipinjam"), HILANG("Hilang"), SELESAI("Selesai"), TERLAMBAT("Terlambat");

    private final String status;

    private StatusPeminjaman(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
