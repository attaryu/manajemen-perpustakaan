package com.manajemen.perpustakaan.view.column.action;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.manajemen.perpustakaan.view.column.IdGetter;

public class FlexibleActionColumnEditor extends AbstractCellEditor implements TableCellEditor {

  private FlexibleActionPanel panel;
  private JTable table;
  private int row;
  private IdGetter idGetter;

  public FlexibleActionColumnEditor(List<ActionButton> actions, IdGetter idGetter) {
    this.panel = new FlexibleActionPanel(actions);
    this.idGetter = idGetter;
  }

  private String getId() {
    if (this.idGetter == null || this.table == null ||
        this.row < 0 || this.row >= this.table.getRowCount()) {
      return null;
    }
    return this.idGetter.getId(this.row);
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {

    this.table = table;
    this.row = row;

    // Set current ID untuk panel
    String id = getId();
    panel.setCurrentId(id);

    // Set background
    if (isSelected) {
      panel.setBackground(table.getSelectionBackground());
    } else {
      panel.setBackground(table.getBackground());
    }

    return panel;
  }

  @Override
  public Object getCellEditorValue() {
    return panel.getCurrentId();
  }

  @Override
  public boolean isCellEditable(java.util.EventObject e) {
    return true;
  }
}
