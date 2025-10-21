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

public class JSONStorage<Entity> {
    private final Path filePath;
    private final Gson gson;
    private final Type listType;

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

    public void put(ArrayList<Entity> list) {
        try (Writer writer = new FileWriter(this.filePath.toFile())) {
            this.gson.toJson(list, writer);
        } catch (IOException e) {
            System.err.println("Error saat menulis ke file: " + e.getMessage());
        }
    }
}

