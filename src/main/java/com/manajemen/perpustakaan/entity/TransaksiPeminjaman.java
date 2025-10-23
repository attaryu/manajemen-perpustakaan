/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

import java.util.Date;

import com.manajemen.perpustakaan.entity.enumeration.StatusEksemplar;
import com.manajemen.perpustakaan.entity.enumeration.StatusPeminjaman;

/**
 * Kelas yang merepresentasikan entitas Transaksi Peminjaman dalam sistem perpustakaan.
 * Menyimpan informasi peminjaman buku oleh mahasiswa termasuk tanggal pinjam, 
 * tanggal jatuh tempo, tanggal kembali, dan status peminjaman.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class TransaksiPeminjaman {
    /** ID unik transaksi peminjaman yang bersifat immutable */
    private final String id;
    
    /** NRP mahasiswa peminjam yang bersifat immutable */
    private final String nrpPeminjam;
    
    /** Nomor eksemplar buku yang dipinjam yang bersifat immutable */
    private final String nomorEksemplar;
    
    /** Tanggal buku dipinjam */
    private Date tanggalPinjam;
    
    /** Tanggal buku dikembalikan (null jika belum dikembalikan) */
    private Date tanggalKembali;
    
    /** Tanggal jatuh tempo pengembalian buku */
    private Date tanggalJatuhTempo;
    
    /** Status peminjaman saat ini */
    private StatusPeminjaman status;

    /** Objek mahasiswa peminjam (transient, tidak disimpan dalam JSON) */
    private transient Mahasiswa peminjam;
    
    /** Objek eksemplar buku yang dipinjam (transient, tidak disimpan dalam JSON) */
    private transient EksemplarBuku eksemplarBuku;

    /**
     * Konstruktor private untuk membuat instance TransaksiPeminjaman.
     * 
     * @param id ID unik transaksi
     * @param peminjam mahasiswa yang meminjam
     * @param eksemplarBuku eksemplar buku yang dipinjam
     * @param tanggalPinjam tanggal peminjaman
     * @param tanggalKembali tanggal pengembalian (bisa null)
     * @param tanggalJatuhTempo tanggal jatuh tempo
     * @param status status peminjaman
     */
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

    /**
     * Factory method untuk membuat transaksi peminjaman baru.
     * Otomatis mengatur status eksemplar menjadi DIPINJAM dan status transaksi menjadi DIPINJAM.
     * 
     * @param peminjam mahasiswa yang meminjam
     * @param eksemplarBuku eksemplar buku yang dipinjam
     * @param tanggalJatuhTempo tanggal jatuh tempo pengembalian
     * @return instance TransaksiPeminjaman yang baru dibuat
     */
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

    /**
     * Mendapatkan ID transaksi.
     * 
     * @return ID transaksi
     */
    public String getId() {
        return this.id;
    }

    /**
     * Mendapatkan objek mahasiswa peminjam.
     * 
     * @return objek Mahasiswa
     */
    public Mahasiswa getPeminjam() {
        return this.peminjam;
    }

    /**
     * Mengatur objek mahasiswa peminjam.
     * 
     * @param peminjam objek Mahasiswa
     */
    public void setPeminjam(Mahasiswa peminjam) {
        this.peminjam = peminjam;
    }

    /**
     * Mendapatkan NRP mahasiswa peminjam.
     * 
     * @return NRP mahasiswa
     */
    public String getNrpPeminjam() {
        return this.nrpPeminjam;
    }

    /**
     * Mendapatkan nomor eksemplar buku yang dipinjam.
     * 
     * @return nomor eksemplar
     */
    public String getNomorEksemplar() {
        return this.nomorEksemplar;
    }

    /**
     * Mendapatkan objek eksemplar buku yang dipinjam.
     * 
     * @return objek EksemplarBuku
     */
    public EksemplarBuku getEksemplarBuku() {
        return this.eksemplarBuku;
    }

    /**
     * Mengatur objek eksemplar buku.
     * 
     * @param eksemplarBuku objek EksemplarBuku
     */
    public void setEksemplarBuku(EksemplarBuku eksemplarBuku) {
        this.eksemplarBuku = eksemplarBuku;
    }

    /**
     * Mendapatkan tanggal peminjaman.
     * 
     * @return tanggal pinjam
     */
    public Date getTanggalPinjam() {
        return this.tanggalPinjam;
    }

    /**
     * Mengatur tanggal peminjaman.
     * 
     * @param tanggalPinjam tanggal pinjam yang baru
     */
    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    /**
     * Mendapatkan tanggal pengembalian buku.
     * 
     * @return tanggal kembali, null jika belum dikembalikan
     */
    public Date getTanggalKembali() {
        return this.tanggalKembali;
    }

    /**
     * Mengatur tanggal pengembalian buku.
     * Hanya dapat diatur jika status adalah TERLAMBAT atau SELESAI.
     * 
     * @param tanggalKembali tanggal pengembalian
     */
    public void setTanggalKembali(Date tanggalKembali) {
        if (this.status != StatusPeminjaman.TERLAMBAT && this.status != StatusPeminjaman.SELESAI) {
            return;
        }

        this.tanggalKembali = tanggalKembali;
    }

    /**
     * Mendapatkan tanggal jatuh tempo pengembalian.
     * 
     * @return tanggal jatuh tempo
     */
    public Date getTanggalJatuhTempo() {
        return this.tanggalJatuhTempo;
    }

    /**
     * Mengatur tanggal jatuh tempo pengembalian.
     * 
     * @param tanggalJatuhTempo tanggal jatuh tempo yang baru
     */
    public void setTanggalJatuhTempo(Date tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    /**
     * Mendapatkan status peminjaman saat ini.
     * 
     * @return status peminjaman
     */
    public StatusPeminjaman getStatus() {
        return this.status;
    }

    /**
     * Mengatur status peminjaman.
     * Method ini akan otomatis mengatur status eksemplar dan tanggal kembali 
     * sesuai dengan status yang diberikan:
     * - TERLAMBAT/SELESAI: mengatur eksemplar menjadi TERSEDIA dan mengisi tanggal kembali
     * - HILANG: mengatur eksemplar menjadi HILANG dan menghapus tanggal kembali
     * - DIPINJAM: mengatur eksemplar menjadi DIPINJAM dan menghapus tanggal kembali
     * 
     * @param status status peminjaman yang baru
     */
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
