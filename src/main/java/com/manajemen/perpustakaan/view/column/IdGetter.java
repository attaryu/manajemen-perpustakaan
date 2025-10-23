package com.manajemen.perpustakaan.view.column;

/**
 * Interface untuk mendapatkan ID dari row table.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public interface IdGetter {
    /**
     * Mendapatkan ID dari row tertentu.
     * 
     * @param row index row
     * @return ID record pada row tersebut
     */
    String getId(int row);
}
