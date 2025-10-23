package com.manajemen.perpustakaan.entity.enumeration;

/**
 * Enum yang merepresentasikan status transaksi peminjaman buku.
 * Status menentukan kondisi saat ini dari peminjaman.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public enum StatusPeminjaman {
    /** Buku sedang dalam status dipinjam */
    DIPINJAM("Dipinjam"), 
    
    /** Buku hilang dan tidak dikembalikan */
    HILANG("Hilang"), 
    
    /** Peminjaman selesai dan buku telah dikembalikan tepat waktu */
    SELESAI("Selesai"), 
    
    /** Buku dikembalikan terlambat */
    TERLAMBAT("Terlambat");

    /** Representasi string dari status */
    private final String status;

    /**
     * Konstruktor untuk enum StatusPeminjaman.
     * 
     * @param status representasi string dari status
     */
    private StatusPeminjaman(String status) {
        this.status = status;
    }

    /**
     * Mendapatkan enum StatusPeminjaman berdasarkan string status (case-insensitive).
     * 
     * @param status string status yang dicari
     * @return StatusPeminjaman yang sesuai, atau null jika tidak ditemukan
     */
    public static StatusPeminjaman getStatus(String status) {
        for (StatusPeminjaman statusPeminjaman : StatusPeminjaman.values()) {
            if (statusPeminjaman.status.equalsIgnoreCase(status)) {
                return statusPeminjaman;
            }
        }

        return null;
    }

    /**
     * Mengembalikan representasi string dari status.
     * 
     * @return string status
     */
    @Override
    public String toString() {
        return this.status;
    }
}
