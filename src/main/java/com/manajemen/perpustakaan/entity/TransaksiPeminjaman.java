/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

import com.manajemen.perpustakaan.entity.enumeration.StatusPeminjaman;

import java.time.LocalDate;

public class TransaksiPeminjaman {
    private final String id;
    private final String nrpPeminjam;
    private final String nomorEksemplar;
    private final LocalDate tanggalPinjam;
    private final LocalDate tanggalJatuhTempo;
    private LocalDate tanggalKembali;
    private StatusPeminjaman status;

    private transient Mahasiswa peminjam;
    private transient EksemplarBuku eksemplarBuku;

    public TransaksiPeminjaman(String id, Mahasiswa peminjam, EksemplarBuku eksemplarBuku, LocalDate tanggalPinjam, LocalDate tanggalKembali, LocalDate tanggalJatuhTempo, StatusPeminjaman status) {
        this.peminjam = peminjam;
        this.eksemplarBuku = eksemplarBuku;

        this.id = id;
        this.nrpPeminjam = this.peminjam.getNrp();
        this.nomorEksemplar = this.eksemplarBuku.getNomorEksemplar();
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public Mahasiswa getPeminjam() {
        return this.peminjam;
    }

    public void setPeminjam(Mahasiswa peminjam) {
        this.peminjam = peminjam;
    }

    public String getNrpPeminjam() {
        return this.nrpPeminjam;
    }

    public String getNomorEksemplar() {
        return this.nomorEksemplar;
    }

    public EksemplarBuku getEksemplarBuku() {
        return this.eksemplarBuku;
    }

    public void setEksemplarBuku(EksemplarBuku eksemplarBuku) {
        this.eksemplarBuku = eksemplarBuku;
    }

    public LocalDate getTanggalPinjam() {
        return this.tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return this.tanggalKembali;
    }

    public LocalDate getTanggalJatuhTempo() {
        return this.tanggalJatuhTempo;
    }

    public StatusPeminjaman getStatus() {
        return this.status;
    }
}
