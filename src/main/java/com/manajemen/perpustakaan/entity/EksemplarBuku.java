/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

import com.manajemen.perpustakaan.entity.enumeration.StatusEksemplar;

/**
 * Kelas yang merepresentasikan entitas Eksemplar Buku dalam sistem perpustakaan.
 * Satu buku dapat memiliki banyak eksemplar dengan nomor unik masing-masing.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class EksemplarBuku {

    /** Nomor unik untuk identifikasi eksemplar buku yang bersifat immutable */
    private final String nomorEksemplar;
    
    /** ISBN buku yang dimiliki eksemplar ini */
    private final String isbn;
    
    /** Status ketersediaan eksemplar */
    private StatusEksemplar status;

    /** Detail informasi buku (transient, tidak disimpan dalam JSON) */
    private transient Buku detailBuku;

    /**
     * Konstruktor untuk membuat instance EksemplarBuku.
     * 
     * @param nomorEksemplar nomor unik eksemplar
     * @param detailBuku informasi detail buku
     * @param status status ketersediaan eksemplar
     */
    public EksemplarBuku(String nomorEksemplar, Buku detailBuku, StatusEksemplar status) {
        this.nomorEksemplar = nomorEksemplar;
        this.isbn = detailBuku.getIsbn();
        this.detailBuku = detailBuku;
        this.status = status;
    }

    /**
     * Factory method untuk membuat eksemplar buku baru dengan nomor otomatis (UUID).
     * Status awal eksemplar adalah TERSEDIA.
     * 
     * @param detailBuku informasi detail buku
     * @return instance EksemplarBuku yang baru dibuat
     */
    public static EksemplarBuku create(Buku detailBuku) {
        String nomorEksemplar = java.util.UUID.randomUUID().toString();
        return new EksemplarBuku(nomorEksemplar, detailBuku, StatusEksemplar.TERSEDIA);
    }

    /**
     * Mendapatkan nomor eksemplar.
     * 
     * @return nomor eksemplar
     */
    public String getNomorEksemplar() {
        return this.nomorEksemplar;
    }

    /**
     * Mendapatkan ISBN buku dari eksemplar ini.
     * 
     * @return ISBN buku
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Mendapatkan status ketersediaan eksemplar.
     * 
     * @return status eksemplar
     */
    public StatusEksemplar getStatus() {
        return this.status;
    }

    /**
     * Mendapatkan detail informasi buku.
     * 
     * @return objek Buku yang berisi detail informasi
     */
    public Buku getDetailBuku() {
        return this.detailBuku;
    }

    /**
     * Mengatur detail informasi buku.
     * 
     * @param detailBuku objek Buku yang berisi detail informasi
     */
    public void setDetailBuku(Buku detailBuku) {
        this.detailBuku = detailBuku;
    }

    /**
     * Mengatur status ketersediaan eksemplar.
     * 
     * @param status status baru untuk eksemplar
     */
    public void setStatus(StatusEksemplar status) {
        this.status = status;
    }

    /**
     * Mengecek apakah eksemplar tersedia untuk dipinjam.
     * 
     * @return true jika status adalah TERSEDIA, false jika tidak
     */
    public boolean isAvailable() {
        return this.status == StatusEksemplar.TERSEDIA;
    }
}
