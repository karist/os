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
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import os.model.PdfList;
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
    Controller con;

    public WsController(Ws pane, JLayeredPane msPanel, JButton startButton, Landing njp, MainScreen x) {
        this.pane = pane;
        this.msPanel = msPanel;
        this.startButton = startButton;
        helpButton = pane.getHelpButton();
        aboutButton = pane.getAboutButton();
        this.njp = njp;
        this.x = x;
        cl = (CardLayout) (msPanel.getLayout());
        buttonListener();
        con = new Controller(x.getGettingStarted2().getjButton1(), x.getGettingStarted2().getjTextArea2(), x.getGettingStarted2().getDisplayArea());
    }

    public void buttonListener() {
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
                PdfList pList = null;
                try {
                    pList = PdfList.fromDirectory(new File("E:\\arsendi\\Documents\\NetBeansProjects\\OS\\pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(WsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(WsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TikaException ex) {
                    Logger.getLogger(WsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                x.getGettingStarted2().getjTextArea2().append(pList.getNames());
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
        
        x.getGettingStarted2().getjButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ForkJoinCon fjc = new ForkJoinCon();
                x.getGettingStarted2().getDisplayArea().setText("\nNumber of Processors:  " + fjc.getNumberOfProcessors());
                try {
                    x.getGettingStarted2().getDisplayArea().append("\nStarting....");
                    fjc.go();
                } catch (IOException ex) {
                    Logger.getLogger(WsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(WsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TikaException ex) {
                    Logger.getLogger(WsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                x.getGettingStarted2().getDisplayArea().append("\nSingle thread process took " + fjc.getSingleThreadTimes() + "ms");
                x.getGettingStarted2().getDisplayArea().append("\nMultithread process took " + fjc.getMultiThreadTimes() + "ms");
                cl.show(pane.getParent(), "startCard");
            }
        });

        x.getGettingStarted2().getjButton2().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(pane.getParent(), "welcomeCard");
            }
        }
        );
    }
}
