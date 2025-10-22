package com.manajemen.perpustakaan.entity.enumeration;

public enum StatusPeminjaman {
    DIPINJAM("Dipinjam"), HILANG("Hilang"), SELESAI("Selesai"), TERLAMBAT("Terlambat");

    private final String status;

    private StatusPeminjaman(String status) {
        this.status = status;
    }

    public static StatusPeminjaman getStatus(String status) {
        System.out.println("status from getStatus: " + status);
        for (StatusPeminjaman statusPeminjaman : StatusPeminjaman.values()) {
            if (statusPeminjaman.status.equalsIgnoreCase(status)) {
                return statusPeminjaman;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
