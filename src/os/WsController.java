/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import os.view.Ws;
import os.view.MainScreen;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

/**
 *
 * @author nugraha
 */
public final class WsController {
    Ws pane;
    MainScreen msPanel;
    JButton startButton, helpButton, aboutButton;
    CardLayout cl;

    public WsController(Ws pane, JLayeredPane msPanel, JButton startButton) {
        this.pane = pane;
        this.msPanel = this.msPanel;
        this.startButton = startButton;
        helpButton = pane.getHelpButton();
        aboutButton = pane.getAboutButton();
        cl = (CardLayout)(msPanel.getLayout());
        buttonListener();
    }
    
    public void buttonListener(){
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "startCard");
            }
        });
        
        pane.getAboutButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "aboutCard");
            }
        });
        
        pane.getHelpButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "howCard");
            }
        });
    }
}
