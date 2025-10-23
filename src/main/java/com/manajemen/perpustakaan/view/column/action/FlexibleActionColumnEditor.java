package com.manajemen.perpustakaan.view.column.action;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.manajemen.perpustakaan.view.column.IdGetter;

/**
 * Table cell editor untuk kolom action yang fleksibel.
 * Menampilkan action buttons yang dapat dikonfigurasi pada cell table.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class FlexibleActionColumnEditor extends AbstractCellEditor implements TableCellEditor {

  private final List<ActionButton> actions;
  private FlexibleActionPanel currentPanel;
  private JTable table;
  private int currentRow;
  private IdGetter idGetter;

  /**
   * Konstruktor FlexibleActionColumnEditor.
   * 
   * @param actions list action button yang akan ditampilkan
   * @param idGetter getter untuk mendapatkan ID dari row
   */
  public FlexibleActionColumnEditor(List<ActionButton> actions, IdGetter idGetter) {
    this.actions = actions;
    this.idGetter = idGetter;
    this.currentRow = -1;
  }

  private String getCurrentId() {
    if (this.idGetter == null || this.table == null ||
        this.currentRow < 0 || this.currentRow >= this.table.getRowCount()) {
      return null;
    }

    return this.idGetter.getId(this.currentRow);
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {

    this.table = table;
    this.currentRow = row;

    String currentId = getCurrentId();

    List<ActionButton> wrappedActions = actions.stream()
        .map(action -> {
          return new ActionButton.Builder()
              .label(action.getLabel())
              .icon(action.getIcon())
              .backgroundColor(action.getBackgroundColor())
              .foregroundColor(action.getForegroundColor())
              .callback((_) -> {
                // currentId di-capture saat panel dibuat
                if (currentId != null && action.getCallback() != null) {
                  action.getCallback().accept(currentId);
                }
                fireEditingStopped();
              })
              .build();
        })
        .toList();

    this.currentPanel = new FlexibleActionPanel(wrappedActions);
    this.currentPanel.setCurrentId(currentId);

    // Set background
    if (isSelected) {
      currentPanel.setBackground(table.getSelectionBackground());
    } else {
      currentPanel.setBackground(table.getBackground());
    }

    return currentPanel;
  }

  @Override
  public Object getCellEditorValue() {
    return currentPanel != null ? currentPanel.getCurrentId() : null;
  }

  @Override
  public boolean isCellEditable(java.util.EventObject e) {
    return true;
  }
}