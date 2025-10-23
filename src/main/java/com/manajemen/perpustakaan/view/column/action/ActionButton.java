// ActionButton.java
package com.manajemen.perpustakaan.view.column.action;

import java.awt.Color;
import java.util.function.Consumer;

/**
 * Model untuk konfigurasi action button dalam table column.
 * Menggunakan Builder pattern untuk memudahkan konfigurasi.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class ActionButton {
  /** Label teks button */
  private String label;
  
  /** Icon button */
  private String icon;
  
  /** Warna background button */
  private Color backgroundColor;
  
  /** Warna foreground/text button */
  private Color foregroundColor;
  
  /** Lebar button */
  private int width;
  
  /** Callback yang dipanggil saat button diklik, menerima ID */
  private Consumer<String> callback;

  /**
   * Private constructor, gunakan Builder untuk membuat instance.
   * 
   * @param builder Builder yang berisi konfigurasi button
   */
  private ActionButton(Builder builder) {
    this.label = builder.label;
    this.icon = builder.icon;
    this.backgroundColor = builder.backgroundColor;
    this.foregroundColor = builder.foregroundColor;
    this.callback = builder.callback;
    this.width = builder.width;
  }

  /**
   * Mendapatkan label button.
   * 
   * @return label button
   */
  public String getLabel() {
    return label;
  }

  /**
   * Mendapatkan icon button.
   * 
   * @return icon button
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Mendapatkan warna background button.
   * 
   * @return warna background
   */
  public Color getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Mendapatkan warna foreground/text button.
   * 
   * @return warna foreground
   */
  public Color getForegroundColor() {
    return foregroundColor;
  }

  /**
   * Mendapatkan callback button.
   * 
   * @return callback function
   */
  public Consumer<String> getCallback() {
    return callback;
  }

  /**
   * Mendapatkan lebar button.
   * 
   * @return lebar button dalam pixel
   */
  public int getWidth() {
    return width;
  }

  /**
   * Mengatur callback button.
   * 
   * @param callback callback function yang akan dipanggil
   */
  public void setCallback(Consumer<String> callback) {
    this.callback = callback;
  }

  /**
   * Builder untuk membuat ActionButton dengan konfigurasi yang mudah.
   * Implementasi Builder pattern untuk memudahkan pembuatan object.
   */
  public static class Builder {
    private String label;
    private String icon;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private Color foregroundColor = Color.BLACK;
    private Consumer<String> callback;
    private int width = 70;

    /**
     * Mengatur label button.
     * 
     * @param label label button
     * @return builder instance
     */
    public Builder label(String label) {
      this.label = label;
      return this;
    }

    /**
     * Mengatur icon button.
     * 
     * @param icon icon button
     * @return builder instance
     */
    public Builder icon(String icon) {
      this.icon = icon;
      return this;
    }

    /**
     * Mengatur warna background button.
     * 
     * @param color warna background
     * @return builder instance
     */
    public Builder backgroundColor(Color color) {
      this.backgroundColor = color;
      return this;
    }

    /**
     * Mengatur warna foreground/text button.
     * 
     * @param color warna foreground
     * @return builder instance
     */
    public Builder foregroundColor(Color color) {
      this.foregroundColor = color;
      return this;
    }

    /**
     * Mengatur lebar button.
     * 
     * @param width lebar button dalam pixel
     * @return builder instance
     */
    public Builder width(int width) {
      this.width = width;
      return this;
    }

    /**
     * Mengatur callback yang dipanggil saat button diklik.
     * 
     * @param callback callback function
     * @return builder instance
     */
    public Builder callback(Consumer<String> callback) {
      if (callback != null) {
        this.callback = callback;
      }

      return this;
    }

    /**
     * Membangun ActionButton dari konfigurasi yang sudah diatur.
     * 
     * @return ActionButton instance
     * @throws IllegalStateException jika label dan icon keduanya null
     */
    public ActionButton build() {
      if (label == null && icon == null) {
        throw new IllegalStateException("Either label or icon must be set");
      }

      return new ActionButton(this);
    }
  }
}
