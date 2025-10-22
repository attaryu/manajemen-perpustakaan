package com.manajemen.perpustakaan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
  static private SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

  static public String toString(java.util.Date date) {
    if (date == null) {
      return "";
    }

    return simpleDate.format(date);
  }

  static public Date toDate(String dateStr) {
    if (dateStr == null || dateStr.isEmpty()) {
      return null;
    }

    try {
      return simpleDate.parse(dateStr);
    } catch (java.text.ParseException e) {
      System.err.println("Gagal mengurai tanggal: " + e.getMessage());
      return null;
    }
  }
}
