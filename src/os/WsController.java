/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import javax.swing.JButton;

/**
 *
 * @author nugraha
 */
public class WsController {
    Ws pane;
    JButton startButton, helpButton, aboutButton;

    public WsController(Ws pane) {
        this.pane = pane;
        startButton = pane.getStartButton();
        helpButton = pane.getHelpButton();
        aboutButton = pane.getAboutButton();
    }
    
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    } 
}
