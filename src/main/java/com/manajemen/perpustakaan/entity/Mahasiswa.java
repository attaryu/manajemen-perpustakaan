/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

/**
 *
 * @author mattar
 */
public class Mahasiswa {
    private final String nrp;
    private final String nama;
    private final String prodi;

    public Mahasiswa(String nrp, String nama, String prodi) {
        this.nrp = nrp;
        this.nama = nama;
        this.prodi = prodi;
    }

    public static Mahasiswa create(String nrp, String nama, String prodi) {
        return new Mahasiswa(nrp, nama, prodi);
    }

    public String getNrp() {
        return this.nrp;
    }

    public String getNama() {
        return this.nama;
    }

    public String getProdi() {
        return this.prodi;
    }
}
