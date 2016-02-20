/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.view;

import javax.swing.JButton;
import javax.swing.JTextArea;
import os.Controller;

/**
 *
 * @author nugraha
 */
public class AutoPanel extends javax.swing.JPanel {
    Controller con;
    /**
     * Creates new form GettingStarted
     */
    public AutoPanel() {
        initComponents();
        con = new Controller(startButton, jTextArea2, displayArea);
    }

    public JButton getjButton1() {
        return startButton;
    }

    public void setjButton1(JButton jButton1) {
        this.startButton = jButton1;
    }

    public JButton getjButton2() {
        return closeAuto;
    }

    public void setjButton2(JButton jButton2) {
        this.closeAuto = jButton2;
    }

    public JTextArea getDisplayArea() {
        return displayArea;
    }

    public void setDisplayArea(JTextArea displayArea) {
        this.displayArea = displayArea;
    }

    public JTextArea getjTextArea2() {
        return jTextArea2;
    }

    public void setjTextArea2(JTextArea jTextArea2) {
        this.jTextArea2 = jTextArea2;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        closeAuto = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("Instruction");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("About");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setPreferredSize(new java.awt.Dimension(450, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Trajan Pro 3", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("PDF Classifier");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 70));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        displayArea.setColumns(20);
        displayArea.setRows(5);
        jScrollPane2.setViewportView(displayArea);

        closeAuto.setText("Close");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeAuto)
                .addGap(207, 207, 207))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeAuto)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 500, 280));

        startButton.setFont(new java.awt.Font("Trajan Pro 3", 1, 14)); // NOI18N
        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/press-play-button.png"))); // NOI18N
        startButton.setText("START");
        startButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        startButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/press-play-button.png"))); // NOI18N
        add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 60));

        jButton1.setText("Close");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeAuto;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
