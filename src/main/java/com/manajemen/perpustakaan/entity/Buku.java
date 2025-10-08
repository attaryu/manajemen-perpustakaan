/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

public class Buku {
    private final String isbn;
    private final String judul;
    private final String penulis;
    private final String penerbit;
    private final int jumlahHalaman;

    public Buku(String isbn, String judul, String penulis, String penerbit, int jumlahHalaman) {
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getJudul() {
        return this.judul;
    }

    public String getPenulis() {
        return this.penulis;
    }

    public String getPenerbit() {
        return this.penerbit;
    }

    public int getJumlahHalaman() {
        return this.jumlahHalaman;
    }
}
