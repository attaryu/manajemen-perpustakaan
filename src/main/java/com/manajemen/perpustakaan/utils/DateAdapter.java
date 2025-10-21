package com.manajemen.perpustakaan.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends TypeAdapter<Date> {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // ISO date format

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(formatter.format(value));
        }
    }

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
