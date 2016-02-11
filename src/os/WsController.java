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
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import os.view.Landing;

/**
 *
 * @author nugraha
 */
public final class WsController {
    Ws pane;
    JLayeredPane msPanel;
    JButton startButton, helpButton, aboutButton;
    CardLayout cl;
    Landing njp;
    MainScreen x;

    public WsController(Ws pane, JLayeredPane msPanel, JButton startButton, Landing njp, MainScreen x) {
        this.pane = pane;
        this.msPanel = msPanel;
        this.startButton = startButton;
        helpButton = pane.getHelpButton();
        aboutButton = pane.getAboutButton();
        this.njp = njp;
        this.x = x;
        cl = (CardLayout)(msPanel.getLayout());
        buttonListener();
        
    }
    
    public void buttonListener(){
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                cl.show(pane.getParent(), "startCard");
                cl.show(pane.getParent(), "selectCard");
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
        
        njp.getAutoBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "startCard");
            }
        });
        
        njp.getManButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "manual");
            }
        });
        
        njp.getjButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "welcomeCard");
            }
        });
        
        x.getAboutUs1().getjButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "welcomeCard");
            }
        });
        
        x.getHowTo1().getjButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "welcomeCard");
            }
        });
        
        x.getGettingStarted2().getjButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "welcomeCard");
            }
        });
        
        x.getManualPanel1().getSelectingFiles1().getjButton2().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "welcomeCard");
            }
        });
    }
}
