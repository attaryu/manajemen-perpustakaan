package com.manajemen.perpustakaan;

import javax.swing.SwingUtilities;

public class ManajemenPerpustakaan {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainController mainController = new MainController();
            mainController.start();
        });
    }
}
