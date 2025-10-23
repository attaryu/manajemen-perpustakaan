package com.manajemen.perpustakaan;

import javax.swing.SwingUtilities;

/**
 * Kelas utama aplikasi Manajemen Perpustakaan.
 * Kelas ini merupakan entry point dari aplikasi yang menggunakan Swing untuk GUI.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class ManajemenPerpustakaan {

    /**
     * Method main untuk menjalankan aplikasi.
     * Menggunakan SwingUtilities.invokeLater untuk memastikan GUI dibuat pada Event Dispatch Thread.
     * 
     * @param args argumen command line (tidak digunakan)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainController mainController = new MainController();
            mainController.start();
        });
    }
}
