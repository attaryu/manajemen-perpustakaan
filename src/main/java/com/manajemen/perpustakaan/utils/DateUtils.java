package com.manajemen.perpustakaan.utils;

public class DateUtils {
  static public String format(java.util.Date date) {
    if (date == null) {
      return null;
    }

    return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
  }
}
