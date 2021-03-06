package os.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nugraha
 */
public class Ws extends javax.swing.JPanel {
    /**
     * Creates new form NewJPanel
     */
    public Ws() {
        initComponents();
    }

    public JButton getAboutButton() {
        return aboutButton;
    }

    public void setAboutButton(JButton aboutButton) {
        this.aboutButton = aboutButton;
    }

    public JButton getHelpButton() {
        return howButton;
    }

    public void setHelpButton(JButton helpButton) {
        this.howButton = helpButton;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JPanel getWsPanel() {
        return wsPanel;
    }

    public void setWsPanel(JPanel wsPanel) {
        this.wsPanel = wsPanel;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        howButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 350));

        wsPanel.setPreferredSize(new java.awt.Dimension(450, 300));
        wsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Trajan Pro 3", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PDF Classifier");
        wsPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 220, 40));

        startButton.setBackground(new java.awt.Color(204, 204, 204));
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/circular30.png"))); // NOI18N
        startButton.setToolTipText("Getting Started");
        startButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        startButton.setPreferredSize(new java.awt.Dimension(60, 60));
        startButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/circular30.png"))); // NOI18N
        wsPanel.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 90, 90));

        aboutButton.setBackground(new java.awt.Color(204, 204, 204));
        aboutButton.setForeground(new java.awt.Color(204, 204, 204));
        aboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/leader.png"))); // NOI18N
        aboutButton.setToolTipText("About");
        aboutButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        aboutButton.setPreferredSize(new java.awt.Dimension(60, 60));
        aboutButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/leader.png"))); // NOI18N
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        wsPanel.add(aboutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 90, 90));

        howButton.setBackground(new java.awt.Color(204, 204, 204));
        howButton.setForeground(new java.awt.Color(255, 255, 255));
        howButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/chemistry8(1).png"))); // NOI18N
        howButton.setToolTipText("Instruction");
        howButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        howButton.setPreferredSize(new java.awt.Dimension(60, 60));
        howButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/chemistry8(1).png"))); // NOI18N
        howButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howButtonActionPerformed(evt);
            }
        });
        wsPanel.add(howButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 90, 90));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/os/png/os.png"))); // NOI18N
        wsPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(wsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void howButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_howButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton howButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel wsPanel;
    // End of variables declaration//GEN-END:variables
}
