package com.manajemen.perpustakaan.view;

import java.util.List;

import com.manajemen.perpustakaan.view.column.IdGetter;
import com.manajemen.perpustakaan.view.column.action.ActionButton;
import com.manajemen.perpustakaan.view.column.action.ActionCallback;
import com.manajemen.perpustakaan.view.column.action.FlexibleActionColumnEditor;
import com.manajemen.perpustakaan.view.column.action.FlexibleActionColumnRenderer;

public class TransaksiPeminjamanView extends javax.swing.JFrame {
    private ActionCallback actionCallback;

    public TransaksiPeminjamanView() {
        this.initComponents();
        this.setupTableColumns();
    }

    private void setupTableColumns() {
        javax.swing.table.TableColumn aksiColumn = data_peminjaman.getColumnModel().getColumn(7);

        List<ActionButton> actions = List.of(
                new ActionButton.Builder().label("Edit").build(),
                new ActionButton.Builder().label("Delete").build());

        aksiColumn.setCellRenderer(new FlexibleActionColumnRenderer(actions));

        if (this.actionCallback != null) {
            actions.get(0).setCallback(this.actionCallback::onEdit);
            actions.get(1).setCallback(this.actionCallback::onDelete);

            IdGetter idGetter = (row) -> {
                Object idValue = data_peminjaman.getValueAt(row, 8);

                return idValue != null ? idValue.toString() : null;
            };

            aksiColumn.setCellEditor(new FlexibleActionColumnEditor(actions, idGetter));
        }

        aksiColumn.setPreferredWidth(actions.size() * 70);

        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        data_peminjaman = new javax.swing.JTable();
        txt_pencarian = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        data_peminjaman.setBackground(new java.awt.Color(204, 204, 204));
        data_peminjaman.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Nama MHS", "NRP", "Buku", "Nomer Eksemplar", "Status Peminjaman", "Tanggal peminjaman",
                        "Tanggal Jatuh Tempo", "Aksi", "id transaksi"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        data_peminjaman.setEditingColumn(0);
        data_peminjaman.setEditingRow(0);
        data_peminjaman.setFocusable(false);
        data_peminjaman.setRowHeight(30);
        data_peminjaman.getTableHeader().setReorderingAllowed(false);
        data_peminjaman.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                data_peminjamanComponentAdded(evt);
            }
        });
        data_peminjaman.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                data_peminjamanComponentHidden(evt);
            }
        });
        jScrollPane1.setViewportView(data_peminjaman);
        if (data_peminjaman.getColumnModel().getColumnCount() > 0) {
            data_peminjaman.getColumnModel().getColumn(8).setMinWidth(0);
            data_peminjaman.getColumnModel().getColumn(8).setPreferredWidth(0);
            data_peminjaman.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        txt_pencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pencarianActionPerformed(evt);
            }
        });

        btn_tambah.setBackground(new java.awt.Color(204, 204, 204));
        btn_tambah.setText("Tambah peminjam");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA PEMINJAMAN BUKU");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cari");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 567,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(403, 403, 403)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        1026, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(53, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_tambahActionPerformed

    }// GEN-LAST:event_btn_tambahActionPerformed

    private void txt_pencarianActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_pencarianActionPerformed
    }// GEN-LAST:event_txt_pencarianActionPerformed

    private void data_peminjamanComponentHidden(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_data_peminjamanComponentHidden
    }// GEN-LAST:event_data_peminjamanComponentHidden

    private void data_peminjamanComponentAdded(java.awt.event.ContainerEvent evt) {// GEN-FIRST:event_data_peminjamanComponentAdded
    }// GEN-LAST:event_data_peminjamanComponentAdded

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new TransaksiPeminjamanView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_tambah;
    private javax.swing.JTable data_peminjaman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_pencarian;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTable getTable() {
        return this.data_peminjaman;
    }

    public javax.swing.table.TableModel getTableModel() {
        return this.data_peminjaman.getModel();
    }

    public javax.swing.JTextField getSearchBar() {
        return this.txt_pencarian;
    }

    public javax.swing.JButton getTambahButton() {
        return this.btn_tambah;
    }

    public void setActionCallback(ActionCallback callback) {
        this.actionCallback = callback;
        setupTableColumns();
    }
}
