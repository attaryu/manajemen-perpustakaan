/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.manajemen.perpustakaan.view;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.manajemen.perpustakaan.utils.DateUtils;

/**
 *
 * @author Wina Rahmalia
 */
public class TambahPeminjamanView extends javax.swing.JFrame {

        public TambahPeminjamanView() {
                initComponents();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {
                jTextField1 = new javax.swing.JTextField();
                jTextField2 = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jTextField3 = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jButton2 = new javax.swing.JButton();
                jButton3 = new javax.swing.JButton();
                jComboBox1 = new javax.swing.JComboBox<>();
                jComboBox2 = new javax.swing.JComboBox<>();
                jLabel7 = new javax.swing.JLabel();
                jDateChooser1 = new com.toedter.calendar.JDateChooser();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jTextField1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextField1ActionPerformed(evt);
                        }
                });

                jTextField2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextField2ActionPerformed(evt);
                        }
                });

                jLabel1.setText("nrp");

                jLabel2.setText("nama");

                jLabel3.setText("prodi");

                jLabel4.setText("tanggal jatuh tempo");

                jLabel5.setText("buku");

                jLabel6.setText("nomor buku");

                jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jButton2.setText("Reset");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton2ActionPerformed(evt);
                        }
                });

                jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jButton3.setText("Submit");
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton3ActionPerformed(evt);
                        }
                });

                jComboBox2.addContainerListener(new java.awt.event.ContainerAdapter() {
                        public void componentAdded(java.awt.event.ContainerEvent evt) {
                                jComboBox2ComponentAdded(evt);
                        }
                });
                jComboBox2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBox2ActionPerformed(evt);
                        }
                });

                jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel7.setText("FROM PEMINJAMAN BUKU");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jButton2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                132,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(199, 199,
                                                                                                                                199)
                                                                                                                .addComponent(jButton3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                132,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(93, 93, 93))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(0, 44, Short.MAX_VALUE)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                37,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                37,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel1,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                37,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jTextField1,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                624,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jTextField2,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                624,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jTextField3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                626,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                layout
                                                                                                                                                .createSequentialGroup()
                                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jLabel4)
                                                                                                                                                                .addComponent(jLabel5,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                37,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jLabel6,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                72,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jComboBox1,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                627,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                false)
                                                                                                                                                                                .addComponent(jComboBox2,
                                                                                                                                                                                                0,
                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                .addComponent(jDateChooser1,
                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                627,
                                                                                                                                                                                                Short.MAX_VALUE)))))))
                                                                .addGap(56, 56, 56))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(jLabel7)
                                                                                .addGap(255, 255, 255)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap(45, Short.MAX_VALUE)
                                                                .addComponent(jLabel7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                27,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(41, 41, 41)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel2))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jTextField2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel1))
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jTextField3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel3))
                                                                .addGap(27, 27, 27)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jDateChooser1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel4))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel5)
                                                                                .addComponent(jComboBox2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jComboBox1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel6))
                                                                .addGap(51, 51, 51)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButton2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                38,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButton3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                38,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(90, 90, 90)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
                this.resetForm();
        }// GEN-LAST:event_jButton2ActionPerformed

        private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
        }// GEN-LAST:event_jTextField2ActionPerformed

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        }// GEN-LAST:event_jButton3ActionPerformed

        private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox2ActionPerformed
        }// GEN-LAST:event_jComboBox2ActionPerformed

        private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        }// GEN-LAST:event_jTextField1ActionPerformed

        private void jComboBox2ComponentAdded(java.awt.event.ContainerEvent evt) {// GEN-FIRST:event_jComboBox2ComponentAdded
        }// GEN-LAST:event_jComboBox2ComponentAdded

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JComboBox<String> jComboBox1;
        private javax.swing.JComboBox<String> jComboBox2;
        private com.toedter.calendar.JDateChooser jDateChooser1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField2;
        private javax.swing.JTextField jTextField3;
        // End of variables declaration//GEN-END:variables

        public javax.swing.JComboBox<String> getBukuDropdown() {
                return this.jComboBox2;
        }

        public javax.swing.JComboBox<String> getEksemplarDropdown() {
                return this.jComboBox1;
        }

        public javax.swing.JButton getSubmitButton() {
                return this.jButton3;
        }

        public Map<String, String> getFormData() {
                Map<String, String> formData = new HashMap<>();

                Date tanggalJatuhTempo = this.jDateChooser1.getDate();

                formData.put("nama", this.jTextField1.getText());
                formData.put("nrp", this.jTextField2.getText());
                formData.put("prodi", this.jTextField3.getText());
                formData.put("tanggalJatuhTempo", tanggalJatuhTempo != null
                                ? DateUtils.toString(tanggalJatuhTempo)
                                : "");
                formData.put("buku", this.jComboBox2.getSelectedItem().toString());
                formData.put("eksemplar", this.jComboBox1.getSelectedItem().toString());

                return formData;
        }

        public void resetForm() {
                this.jTextField1.setText("");
                this.jTextField2.setText("");
                this.jTextField3.setText("");
                this.jDateChooser1.setDate(null);
                this.jComboBox2.setSelectedIndex(0);
                this.jComboBox1.setSelectedIndex(0);
        }
}
