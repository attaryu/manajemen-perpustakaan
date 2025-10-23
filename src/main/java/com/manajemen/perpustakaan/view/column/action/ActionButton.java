// ActionButton.java
package com.manajemen.perpustakaan.view.column.action;

import java.awt.Color;
import java.util.function.Consumer;

public class ActionButton {
  private String label;
  private String icon;
  private Color backgroundColor;
  private Color foregroundColor;
  private int width;
  private Consumer<String> callback; // Callback menerima ID

  private ActionButton(Builder builder) {
    this.label = builder.label;
    this.icon = builder.icon;
    this.backgroundColor = builder.backgroundColor;
    this.foregroundColor = builder.foregroundColor;
    this.callback = builder.callback;
    this.width = builder.width;
  }

  // Getters
  public String getLabel() {
    return label;
  }

  public String getIcon() {
    return icon;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public Color getForegroundColor() {
    return foregroundColor;
  }

  public Consumer<String> getCallback() {
    return callback;
  }

  public int getWidth() {
    return width;
  }

  public void setCallback(Consumer<String> callback) {
    this.callback = callback;
  }

  // Builder Pattern untuk easy configuration
  public static class Builder {
    private String label;
    private String icon;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private Color foregroundColor = Color.BLACK;
    private Consumer<String> callback;
    private int width = 70;

    public Builder label(String label) {
      this.label = label;
      return this;
    }

    public Builder icon(String icon) {
      this.icon = icon;
      return this;
    }

    public Builder backgroundColor(Color color) {
      this.backgroundColor = color;
      return this;
    }

    public Builder foregroundColor(Color color) {
      this.foregroundColor = color;
      return this;
    }

    public Builder width(int width) {
      this.width = width;
      return this;
    }

    public Builder callback(Consumer<String> callback) {
      if (callback != null) {
        this.callback = callback;
      }

      return this;
    }

    public ActionButton build() {
      if (label == null && icon == null) {
        throw new IllegalStateException("Either label or icon must be set");
      }

      return new ActionButton(this);
    }
  }
}
