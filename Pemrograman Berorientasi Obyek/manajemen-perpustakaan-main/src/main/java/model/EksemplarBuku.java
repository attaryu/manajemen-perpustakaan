package com.perpustakaan.model;

/**
 * Kelas entitas untuk copy fisik (eksemplar) dari sebuah Buku.
 * Inilah yang dipinjam oleh mahasiswa.
 */
public class EksemplarBuku {
    private String nomorEksemplar; // Misal: B-001-01 (Buku 001, copy 01)
    private Buku detailBuku; // Relasi Composition
    private StatusEksemplar status;

    // Constructor sesuai diagram
    public EksemplarBuku(String nomorEksemplar, Buku detailBuku) {
        this.nomorEksemplar = nomorEksemplar;
        this.detailBuku = detailBuku;
        this.status = StatusEksemplar.TERSEDIA; // Asumsi: eksemplar baru pasti tersedia
    }

    // Getter dan Setter
    public String getNomorEksemplar() {
        return nomorEksemplar;
    }

    public Buku getDetailBuku() {
        return detailBuku;
    }

    public StatusEksemplar getStatus() {
        return status;
    }

    public void setStatus(StatusEksemplar status) {
        this.status = status;
    }

    /**
     * Ini adalah contoh 'delegate method' seperti di diagram.
     * Mengambil judul dari objek 'detailBuku' yang dimilikinya.
     */
    public String getJudul() {
        return this.detailBuku.getJudul();
    }
    
    // (Opsional) Override toString()
    @Override
    public String toString() {
        return getJudul() + " [Copy: " + nomorEksemplar + "]";
    }
}