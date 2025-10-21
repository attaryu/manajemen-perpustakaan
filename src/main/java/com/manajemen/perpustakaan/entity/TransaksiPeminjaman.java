/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

import com.manajemen.perpustakaan.entity.enumeration.StatusPeminjaman;

import java.util.Date;

public class TransaksiPeminjaman {
    private final String id;
    private final String nrpPeminjam;
    private final String nomorEksemplar;
    private final Date tanggalPinjam;
    private final Date tanggalKembali;
    private Date tanggalJatuhTempo;
    private StatusPeminjaman status;

    private transient Mahasiswa peminjam;
    private transient EksemplarBuku eksemplarBuku;

    private TransaksiPeminjaman(String id, Mahasiswa peminjam, EksemplarBuku eksemplarBuku, Date tanggalPinjam,
            Date tanggalKembali, Date tanggalJatuhTempo, StatusPeminjaman status) {
        this.peminjam = peminjam;
        this.eksemplarBuku = eksemplarBuku;

        this.id = id;
        this.nrpPeminjam = this.peminjam.getNrp();
        this.nomorEksemplar = this.eksemplarBuku.getNomorEksemplar();
        this.tanggalPinjam = new Date();
        this.tanggalKembali = tanggalKembali;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.status = status;
    }

    public static TransaksiPeminjaman create(
            Mahasiswa peminjam,
            EksemplarBuku eksemplarBuku,
            Date tanggalJatuhTempo) {
        return new TransaksiPeminjaman(
                java.util.UUID.randomUUID().toString(),
                peminjam,
                eksemplarBuku,
                new Date(),
                null,
                tanggalJatuhTempo,
                StatusPeminjaman.DIPINJAM);
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

    public Date getTanggalPinjam() {
        return this.tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return this.tanggalKembali;
    }

    public Date getTanggalJatuhTempo() {
        return this.tanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(Date tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public StatusPeminjaman getStatus() {
        return this.status;
    }

    public void setStatus(StatusPeminjaman status) {
        this.status = status;
    }
}
