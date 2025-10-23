package com.manajemen.perpustakaan.view.column.action;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel yang berisi action buttons untuk ditampilkan dalam table cell.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-08
 */
public class FlexibleActionPanel extends JPanel {
    
    private List<JButton> buttons;
    private String currentId;
    
    /**
     * Konstruktor FlexibleActionPanel.
     * 
     * @param actions list action button yang akan ditampilkan dalam panel
     */
    public FlexibleActionPanel(List<ActionButton> actions) {
        this.buttons = new ArrayList<>();
        
        // Setup layout
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        // Create buttons dari config
        for (ActionButton action : actions) {
            JButton button = createButton(action);
            buttons.add(button);
            add(button);
        }
    }
    
    private JButton createButton(ActionButton action) {
        // Create button dengan label/icon
        String buttonText = action.getIcon() != null ? action.getIcon() : "";
        if (action.getLabel() != null) {
            buttonText += (buttonText.isEmpty() ? "" : " ") + action.getLabel();
        }
        
        JButton button = new JButton(buttonText);
        
        // Set colors
        button.setBackground(action.getBackgroundColor());
        button.setForeground(action.getForegroundColor());
        
        // Set size
        button.setPreferredSize(new Dimension(action.getWidth(), 25));
        button.setFocusable(false);
        
        // Add click listener
        button.addActionListener((_) -> {
            if (currentId != null && action.getCallback() != null) {
                action.getCallback().accept(currentId);
            }
        });
        
        return button;
    }
    
    /**
     * Mengatur ID saat ini untuk row yang sedang ditampilkan.
     * 
     * @param id ID record
     */
    public void setCurrentId(String id) {
        this.currentId = id;
    }
    
    /**
     * Mendapatkan ID saat ini.
     * 
     * @return ID record saat ini
     */
    public String getCurrentId() {
        return currentId;
    }
}
