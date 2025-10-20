package com.manajemen.perpustakaan.view;

public interface ActionCallback {
    void onEdit(String id);
    void onDelete(String id);
    void onDataUpdated();
}
