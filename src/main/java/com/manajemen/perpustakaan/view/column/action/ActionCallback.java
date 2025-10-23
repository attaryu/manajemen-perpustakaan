package com.manajemen.perpustakaan.view.column.action;

/**
 * Interface callback untuk action pada table row.
 * Menyediakan method default untuk berbagai action yang dapat di-override.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public interface ActionCallback {
    /**
     * Dipanggil saat button edit diklik.
     * 
     * @param id ID record yang akan diedit
     */
    default void onEdit(String id) {
    }

    /**
     * Dipanggil saat button delete diklik.
     * 
     * @param id ID record yang akan dihapus
     */
    default void onDelete(String id) {
    }

    /**
     * Dipanggil saat button view/detail diklik.
     * 
     * @param id ID record yang akan dilihat
     */
    default void onView(String id) {
    }

    /**
     * Dipanggil saat button create/tambah diklik.
     */
    default void onCreate() {
    }

    /**
     * Dipanggil saat button refresh diklik.
     */
    default void onRefresh() {
    }

    /**
     * Dipanggil saat search dijalankan.
     * 
     * @param query keyword pencarian
     */
    default void onSearch(String query) {
    }
}
