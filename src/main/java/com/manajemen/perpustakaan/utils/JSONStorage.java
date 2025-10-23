package com.manajemen.perpustakaan.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.ArrayList;

/**
 * Generic JSON storage untuk persistence data entity ke file JSON.
 * Menggunakan Gson untuk serialisasi dan deserialisasi.
 * 
 * @param <Entity> tipe entity yang akan disimpan
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class JSONStorage<Entity> {
    /** Path file JSON untuk storage */
    private final Path filePath;
    
    /** Instance Gson untuk serialisasi/deserialisasi */
    private final Gson gson;
    
    /** Tipe data list entity untuk deserialisasi */
    private final Type listType;

    /**
     * Konstruktor yang menginisialisasi JSON storage.
     * 
     * @param filename nama file tanpa ekstensi (akan otomatis ditambah .json)
     * @param listType tipe data list entity untuk deserialisasi
     */
    public JSONStorage(String filename, Type listType) {
        this.filePath = Paths.get("data", filename + ".json");
        this.listType = listType;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateAdapter())
                .setPrettyPrinting()
                .create();

        try {
            Files.createDirectories(this.filePath.getParent());
        } catch (IOException e) {
            System.err.println("Gagal membuat direktori: " + e.getMessage());
        }
    }

    /**
     * Membaca dan mengembalikan data entity dari file JSON.
     * Mengembalikan list kosong jika file tidak ada atau error.
     * 
     * @return ArrayList berisi entity yang dibaca dari file
     */
    public ArrayList<Entity> get() {
        if (!Files.exists(this.filePath)) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(this.filePath)) {
            ArrayList<Entity> data = gson.fromJson(reader, this.listType);
            return (data != null) ? data : new ArrayList<>();
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error saat membaca file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Menyimpan list entity ke file JSON.
     * 
     * @param list ArrayList entity yang akan disimpan
     */
    public void put(ArrayList<Entity> list) {
        try (Writer writer = new FileWriter(this.filePath.toFile())) {
            this.gson.toJson(list, writer);
        } catch (IOException e) {
            System.err.println("Error saat menulis ke file: " + e.getMessage());
        }
    }
}

