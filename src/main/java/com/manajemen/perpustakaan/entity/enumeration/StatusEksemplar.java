/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.manajemen.perpustakaan.entity.enumeration;

public enum StatusEksemplar {
    TERSEDIA("Tersedia"),
    DIPINJAM("Dipinjam");

    private final String status;

    StatusEksemplar(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
