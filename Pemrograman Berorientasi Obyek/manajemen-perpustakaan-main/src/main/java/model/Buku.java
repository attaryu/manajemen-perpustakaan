package model;

/**
 * Kelas entitas untuk data master Buku.
 * Merepresentasikan 'konsep' buku, bukan copy fisiknya.
 */
public class Buku {
    private String isbn;
    private String judul;
    private String penulis;
    private String penerbit;
    private int jumlahHalaman;

    // Constructor sesuai diagram
    public Buku(String isbn, String judul, String penulis, String penerbit, int jumlahHalaman) {
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.jumlahHalaman = jumlahHalaman;
    }

    // Getter methods
    public String getIsbn() {
        return isbn;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }
    
    // (Opsional) Override toString() untuk debugging atau tampilan di JComboBox
    @Override
    public String toString() {
        return judul + " (" + isbn + ")";
    }
}