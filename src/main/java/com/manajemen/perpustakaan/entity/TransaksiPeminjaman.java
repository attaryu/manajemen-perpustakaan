/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

import java.util.Date;

import com.manajemen.perpustakaan.entity.enumeration.StatusEksemplar;
import com.manajemen.perpustakaan.entity.enumeration.StatusPeminjaman;

public class TransaksiPeminjaman {
    private final String id;
    private final String nrpPeminjam;
    private final String nomorEksemplar;
    private Date tanggalPinjam;
    private Date tanggalKembali;
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
        TransaksiPeminjaman transaksi = new TransaksiPeminjaman(
                java.util.UUID.randomUUID().toString(),
                peminjam,
                eksemplarBuku,
                new Date(),
                null,
                tanggalJatuhTempo,
                StatusPeminjaman.DIPINJAM);

        transaksi.getEksemplarBuku().setStatus(StatusEksemplar.DIPINJAM);

        return transaksi;
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

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return this.tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) throws Exception {
        if (this.status != StatusPeminjaman.TERLAMBAT && this.status != StatusPeminjaman.SELESAI) {
            throw new Exception("Tidak dapat mengatur tanggal kembali untuk status peminjaman saat ini");
        }

        this.tanggalKembali = tanggalKembali;
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
        if (this.status == status) {
            return;
        }

        switch (status) {
            case TERLAMBAT:
            case SELESAI:
                if (this.tanggalKembali == null) {
                    this.tanggalKembali = new Date();
                }

                this.eksemplarBuku.setStatus(StatusEksemplar.TERSEDIA);
                break;

            case HILANG:
                this.eksemplarBuku.setStatus(StatusEksemplar.HILANG);
                this.tanggalKembali = null;
                break;

            case DIPINJAM:
                this.eksemplarBuku.setStatus(StatusEksemplar.DIPINJAM);
                this.tanggalKembali = null;
                break;

            default:
                this.tanggalKembali = null;
                break;
        }

        this.status = status;
    }
}
