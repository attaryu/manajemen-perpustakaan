/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manajemen.perpustakaan.entity;

import com.manajemen.perpustakaan.entity.enumeration.StatusEksemplar;

public class EksemplarBuku {

    private final String nomorEksemplar;
    private final String isbn;
    private StatusEksemplar status;

    private transient Buku detailBuku;

    public EksemplarBuku(String nomorEksemplar, Buku detailBuku, StatusEksemplar status) {
        this.nomorEksemplar = nomorEksemplar;
        this.isbn = detailBuku.getIsbn();
        this.detailBuku = detailBuku;
        this.status = status;
    }

    public String getNomorEksemplar() {
        return this.nomorEksemplar;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getStatus() {
        return this.status.toString();
    }

    public Buku getDetailBuku() {
        return this.detailBuku;
    }

    public void setDetailBuku(Buku detailBuku) {
        this.detailBuku = detailBuku;
    }

    public void setStatus(StatusEksemplar status) {
        this.status = status;
    }
}
