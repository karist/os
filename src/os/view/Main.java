/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import os.Controller;

/**
 *
 * @author Ristika Nugraha [12.7350]
 */
public class Main extends javax.swing.JFrame {

    Controller con;

    /**
     * Creates new form Main
     */
    public Main() throws IOException {
        initComponents();
        con = new Controller(browseBtn, selectedTxt, jTextArea1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        browseBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedTxt = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(2, 28, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        browseBtn.setBackground(new java.awt.Color(0, 68, 69));
        browseBtn.setForeground(new java.awt.Color(240, 240, 240));
        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });
        getContentPane().add(browseBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 88, -1, -1));

        selectedTxt.setBackground(new java.awt.Color(240, 240, 240));
        selectedTxt.setColumns(20);
        selectedTxt.setForeground(new java.awt.Color(2, 28, 30));
        selectedTxt.setRows(5);
        jScrollPane1.setViewportView(selectedTxt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 117, 260, 172));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 110, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        con.browse();
    }//GEN-LAST:event_browseBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                // begin with the interactive portion of the program
                new Main().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea selectedTxt;
    // End of variables declaration//GEN-END:variables
}
