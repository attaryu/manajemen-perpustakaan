package com.manajemen.perpustakaan.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Gson TypeAdapter untuk serialisasi dan deserialisasi Date.
 * Menggunakan format ISO date yyyy-MM-dd.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class DateAdapter extends TypeAdapter<Date> {
    /** Formatter untuk format ISO date yyyy-MM-dd */
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Serialisasi Date ke JSON string.
     * 
     * @param out JsonWriter output
     * @param value Date yang akan diserialize
     * @throws IOException jika terjadi error saat menulis
     */
    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(formatter.format(value));
        }
    }

    /**
     * Deserialisasi JSON string menjadi Date.
     * 
     * @param in JsonReader input
     * @return object Date hasil parsing
     * @throws IOException jika terjadi error saat membaca atau parsing tanggal
     */
    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        try {
            return formatter.parse(in.nextString());
        } catch (java.text.ParseException e) {
            throw new IOException("Error parsing date", e);
        }
    }
}
