/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.manajemen.perpustakaan.entity.enumeration;

/**
 * Enum yang merepresentasikan status ketersediaan eksemplar buku.
 * Status menentukan apakah eksemplar dapat dipinjam atau tidak.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public enum StatusEksemplar {
    /** Eksemplar tersedia untuk dipinjam */
    TERSEDIA("Tersedia"),
    
    /** Eksemplar sedang dipinjam */
    DIPINJAM("Dipinjam"),
    
    /** Eksemplar hilang */
    HILANG("Hilang"),
    
    /** Eksemplar rusak */
    RUSAK("Rusak");

    /** Representasi string dari status */
    private final String status;

    /**
     * Konstruktor untuk enum StatusEksemplar.
     * 
     * @param status representasi string dari status
     */
    StatusEksemplar(String status) {
        this.status = status;
    }

    /**
     * Mengembalikan representasi string dari status.
     * 
     * @return string status
     */
    @Override
    public String toString() {
        return this.status;
    }
}
