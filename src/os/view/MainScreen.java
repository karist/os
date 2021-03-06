package os.view;

import javax.swing.JLayeredPane;
import os.Navigate;
import os.WsController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nugraha
 */
public class MainScreen extends javax.swing.JFrame {
    WsController wcon;
    Navigate mcon;

    /**
     * Creates new form NewJFrame
     */
    public MainScreen() {
        initComponents();
        wcon = new WsController(ws1, msPanel, ws1.getStartButton(), newJPanel1, this);
        mcon = new Navigate(manualPanel1);
    }

    public WsController getWcon() {
        return wcon;
    }

    public void setWcon(WsController wcon) {
        this.wcon = wcon;
    }

    public JLayeredPane getMsPanel() {
        return msPanel;
    }

    public void setMsPanel(JLayeredPane msPanel) {
        this.msPanel = msPanel;
    }

    public Ws getUp1() {
        return ws1;
    }

    public void setUp1(Ws up1) {
        this.ws1 = up1;
    }

    public Landing getNewJPanel1() {
        return newJPanel1;
    }

    public void setNewJPanel1(Landing newJPanel1) {
        this.newJPanel1 = newJPanel1;
    }

    public AboutUs getAboutUs1() {
        return aboutUs1;
    }

    public void setAboutUs1(AboutUs aboutUs1) {
        this.aboutUs1 = aboutUs1;
    }

    public AutoPanel getGettingStarted2() {
        return gettingStarted2;
    }

    public void setGettingStarted2(AutoPanel gettingStarted2) {
        this.gettingStarted2 = gettingStarted2;
    }

    public HowTo getHowTo1() {
        return howTo1;
    }

    public void setHowTo1(HowTo howTo1) {
        this.howTo1 = howTo1;
    }

    public ManualPanel getManualPanel1() {
        return manualPanel1;
    }

    public void setManualPanel1(ManualPanel manualPanel1) {
        this.manualPanel1 = manualPanel1;
    }

    public Ws getWs1() {
        return ws1;
    }

    public void setWs1(Ws ws1) {
        this.ws1 = ws1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msPanel = new javax.swing.JLayeredPane();
        ws1 = new os.view.Ws();
        gettingStarted2 = new os.view.AutoPanel();
        manualPanel1 = new os.view.ManualPanel();
        aboutUs1 = new os.view.AboutUs();
        howTo1 = new os.view.HowTo();
        newJPanel1 = new os.view.Landing();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(java.awt.Color.black);

        msPanel.setBackground(new java.awt.Color(0, 0, 0));
        msPanel.setForeground(new java.awt.Color(204, 204, 204));
        msPanel.setPreferredSize(new java.awt.Dimension(450, 350));
        msPanel.setLayout(new java.awt.CardLayout());
        msPanel.add(ws1, "welcomeCard");
        ws1.getAccessibleContext().setAccessibleName("");

        msPanel.add(gettingStarted2, "startCard");
        msPanel.add(manualPanel1, "manual");
        msPanel.add(aboutUs1, "aboutCard");
        msPanel.add(howTo1, "howCard");
        msPanel.add(newJPanel1, "selectCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(msPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(msPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private os.view.AboutUs aboutUs1;
    private os.view.AutoPanel gettingStarted2;
    private os.view.HowTo howTo1;
    private os.view.ManualPanel manualPanel1;
    private javax.swing.JLayeredPane msPanel;
    private os.view.Landing newJPanel1;
    private os.view.Ws ws1;
    // End of variables declaration//GEN-END:variables
}
