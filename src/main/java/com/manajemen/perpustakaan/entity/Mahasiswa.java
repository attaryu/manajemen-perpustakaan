/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

/**
 * Kelas yang merepresentasikan entitas Mahasiswa dalam sistem perpustakaan.
 * Menyimpan informasi mahasiswa yang meminjam buku seperti NRP, nama, dan program studi.
 *
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class Mahasiswa {
    /** Nomor Registrasi Pokok mahasiswa yang bersifat unik dan immutable */
    private final String nrp;
    
    /** Nama lengkap mahasiswa yang bersifat immutable */
    private final String nama;
    
    /** Program studi mahasiswa yang bersifat immutable */
    private final String prodi;

    /**
     * Konstruktor untuk membuat instance Mahasiswa.
     * 
     * @param nrp Nomor Registrasi Pokok mahasiswa
     * @param nama nama lengkap mahasiswa
     * @param prodi program studi mahasiswa
     */
    public Mahasiswa(String nrp, String nama, String prodi) {
        this.nrp = nrp;
        this.nama = nama;
        this.prodi = prodi;
    }

    /**
     * Factory method untuk membuat instance Mahasiswa baru.
     * 
     * @param nrp Nomor Registrasi Pokok mahasiswa
     * @param nama nama lengkap mahasiswa
     * @param prodi program studi mahasiswa
     * @return instance Mahasiswa yang baru dibuat
     */
    public static Mahasiswa create(String nrp, String nama, String prodi) {
        return new Mahasiswa(nrp, nama, prodi);
    }

    /**
     * Mendapatkan NRP mahasiswa.
     * 
     * @return NRP mahasiswa
     */
    public String getNrp() {
        return this.nrp;
    }

    /**
     * Mendapatkan nama mahasiswa.
     * 
     * @return nama mahasiswa
     */
    public String getNama() {
        return this.nama;
    }

    /**
     * Mendapatkan program studi mahasiswa.
     * 
     * @return program studi mahasiswa
     */
    public String getProdi() {
        return this.prodi;
    }
}
