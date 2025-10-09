package com.manajemen.perpustakaan.view;

public class TransaksiPeminjamanView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(TransaksiPeminjamanView.class.getName());

    public TransaksiPeminjamanView() {
        initComponents();
        Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class };

        javax.swing.table.TableColumn aksiColumn = data_peminjaman.getColumnModel().getColumn(7);

        aksiColumn.setCellRenderer(new ActionColumnRenderer());
        aksiColumn.setCellEditor(new ActionColumnEditor());
    }

    public void tambahDataPeminjam(Object[] dataRow) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) data_peminjaman.getModel();

        int barisKosong = -1;

        for (int i = 0; i < model.getRowCount(); i++) {
            // Cek kolom pertama (Nama MHS). Jika nilainya null, berarti baris itu kosong.
            if (model.getValueAt(i, 0) == null) {
                barisKosong = i;
                break;
            }
        }

        if (barisKosong != -1) {
            model.removeRow(barisKosong);
            model.insertRow(barisKosong, dataRow);
        } else {
            model.addRow(dataRow);
        }
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
        data_peminjaman.setModel(
                new javax.swing.table.DefaultTableModel(null, new String[] { "Nama MHS", "NRP", "Buku",
                        "Nomer Eksemplar", "Status Peminjaman", "Tanggal peminjaman", "Tanggal Jatuh Tempo", "Aksi" }) {
                    Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class,
                            java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                            java.lang.Object.class, java.lang.Object.class, java.lang.Double.class };

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                });
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

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA PEMINJAMAN BUKU");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cari");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1066,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txt_pencarian,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 567,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(218, 218, 218)
                                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(90, 90, 90)))));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addGap(18, 18, 18).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_tambahActionPerformed
        // Event akan dihandle oleh controller
    }// GEN-LAST:event_btn_tambahActionPerformed

    private void txt_pencarianActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_pencarianActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_pencarianActionPerformed

    private void data_peminjamanComponentHidden(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_data_peminjamanComponentHidden
        // TODO add your handling code here:
    }// GEN-LAST:event_data_peminjamanComponentHidden

    private void data_peminjamanComponentAdded(java.awt.event.ContainerEvent evt) {// GEN-FIRST:event_data_peminjamanComponentAdded
        // TODO add your handling code here:
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
}
