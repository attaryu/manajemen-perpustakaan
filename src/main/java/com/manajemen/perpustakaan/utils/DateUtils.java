package com.manajemen.perpustakaan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class untuk konversi tanggal antara Date dan String.
 * Menggunakan format tanggal yyyy-MM-dd.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class DateUtils {
  /** SimpleDateFormat dengan format yyyy-MM-dd */
  static private SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * Mengkonversi Date menjadi String dengan format yyyy-MM-dd.
   * 
   * @param date object Date yang akan dikonversi
   * @return string representasi tanggal, atau string kosong jika date null
   */
  static public String toString(java.util.Date date) {
    if (date == null) {
      return "";
    }

    return simpleDate.format(date);
  }

  /**
   * Mengkonversi String menjadi Date dengan format yyyy-MM-dd.
   * 
   * @param dateStr string tanggal yang akan dikonversi
   * @return object Date hasil parsing, atau null jika dateStr null/kosong/invalid
   */
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
