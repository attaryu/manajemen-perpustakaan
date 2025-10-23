/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

/**
 * Kelas yang merepresentasikan entitas Buku dalam sistem perpustakaan.
 * Menyimpan informasi dasar tentang buku seperti ISBN, judul, penulis, penerbit, dan jumlah halaman.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class Buku {
    /** Nomor ISBN buku yang bersifat unik */
    private String isbn;
    
    /** Judul buku */
    private String judul;
    
    /** Nama penulis buku */
    private String penulis;
    
    /** Nama penerbit buku */
    private String penerbit;
    
    /** Jumlah halaman dalam buku */
    private int jumlahHalaman;

    /**
     * Konstruktor untuk membuat instance Buku.
     * 
     * @param isbn ISBN buku
     * @param judul judul buku
     * @param penulis nama penulis buku
     * @param penerbit nama penerbit buku
     * @param jumlahHalaman jumlah halaman dalam buku
     */
    public Buku(String isbn, String judul, String penulis, String penerbit, int jumlahHalaman) {
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.jumlahHalaman = jumlahHalaman;
    }

    /**
     * Mendapatkan ISBN buku.
     * 
     * @return ISBN buku
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Mengatur ISBN buku.
     * 
     * @param isbn ISBN buku yang baru
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Mendapatkan judul buku.
     * 
     * @return judul buku
     */
    public String getJudul() {
        return this.judul;
    }

    /**
     * Mengatur judul buku.
     * 
     * @param judul judul buku yang baru
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * Mendapatkan nama penulis buku.
     * 
     * @return nama penulis
     */
    public String getPenulis() {
        return this.penulis;
    }

    /**
     * Mengatur nama penulis buku.
     * 
     * @param penulis nama penulis yang baru
     */
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    /**
     * Mendapatkan nama penerbit buku.
     * 
     * @return nama penerbit
     */
    public String getPenerbit() {
        return this.penerbit;
    }

    /**
     * Mengatur nama penerbit buku.
     * 
     * @param penerbit nama penerbit yang baru
     */
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    /**
     * Mendapatkan jumlah halaman buku.
     * 
     * @return jumlah halaman
     */
    public int getJumlahHalaman() {
        return this.jumlahHalaman;
    }

    /**
     * Mengatur jumlah halaman buku.
     * 
     * @param jumlahHalaman jumlah halaman yang baru
     */
    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }
}
