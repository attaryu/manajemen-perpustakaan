package com.manajemen.perpustakaan.view.column.action;

public interface ActionCallback {
    default void onEdit(String id) {
    }

    default void onDelete(String id) {
    }

    default void onView(String id) {
    }

    default void onCreate() {
    }

    default void onRefresh() {
    }

    default void onSearch(String query) {
    }
}
