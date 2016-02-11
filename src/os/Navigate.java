/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import os.view.ManualPanel;

/**
 *
 * @author nugraha
 */
public class Navigate {

    ManualPanel mpane;
    File[] files;
    List<String> text, tokenizedText, posText;

    public Navigate(ManualPanel mpane) {
        this.mpane = mpane;
        disableComponent();
        buttonListener();
    }

    public String pdfParse(String uri) throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(new File(uri));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, uri + "Not Found");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);

        //getting the content of the document
//        System.out.println("Contents of the PDF :" + handler.toString());
        //getting metadata of the document
//        System.out.println("Metadata of the PDF:");
//        String[] metadataNames = metadata.names();
//        for (String name : metadataNames) {
//            System.out.println(name + " : " + metadata.get(name));
//        }
//        createTxt(fileName, textPOSTagging(handler.toString()));
        String result = handler.toString();
        return result;
    }

    public void createThread(String a, String b) {
        Thread thread = new Thread(new Runnable() {
            Controller con = new Controller();

            @Override
            public void run() {
                Date date = new Date();
                String tname = Thread.currentThread().getName();
//                switch (a) {
//                    case "convert":
                try {
                    String x = pdfParse(b);
                    text.add(x);
                } catch (IOException | SAXException | TikaException e) {
                    System.out.println(e.getCause());
                }
//                        break;
//                    case "token": {
//                        try {
//                            tokenizedText.add(con.tokenize(b));
//                        } catch (IOException ex) {
//                            Logger.getLogger(ManualController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        break;
//                    }
//                    case "pos": {
//                        posText.add(con.textPOSTagging(b));
//                        break;
//                    }
//                }
            }
        });
        thread.start();
    }

    private void buttonListener() {
        mpane.getSelectingFiles1().getSelectBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getSelectingFiles1().getjTextArea2().setText("");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf", "PDF");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.showOpenDialog(null);

                files = fileChooser.getSelectedFiles();

                mpane.getSelectingFiles1().getjTextArea2().append("You've selected " + files.length + " PDF file(s).\n");
                int count = 1;
                for (File f : files) {
                    mpane.getSelectingFiles1().getjTextArea2().append(count + f.getName() + "\n");
                    
                    count++;
                }
                mpane.getSelectingFiles1().getNext1Btn().setEnabled(true);
            }
        });

        mpane.getSelectingFiles1().getNext1Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setEnabledAt(1, true);
                mpane.getjTabbedPane1().setSelectedIndex(1);
            }
        });

        mpane.getFile2Text1().getConvertBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(files.length);
//                for (File f : files) {
//                    createThread("convert", f.getAbsolutePath());
//                }
                mpane.getFile2Text1().getNext2Btn().setEnabled(true);
            }
        });

        mpane.getFile2Text1().getNext2Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setEnabledAt(2, true);
                mpane.getjTabbedPane1().setSelectedIndex(2);
                mpane.getFile2Text1().getConvertBtn().setEnabled(false);
            }
        });

        mpane.getFile2Text1().getBack1Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setSelectedIndex(0);
            }
        });

        mpane.getTokenizing1().getTokenBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                tokenizedText = new ArrayList<>();
//                for (String t : text) {
//                    createThread("token", t);
//                }
                mpane.getTokenizing1().getNext2Btn().setEnabled(true);
            }
        });

        mpane.getTokenizing1().getBack1Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setSelectedIndex(1);
            }
        });

        mpane.getTokenizing1().getNext2Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setEnabledAt(3, true);
                mpane.getjTabbedPane1().setSelectedIndex(3);
                mpane.getTokenizing1().getTokenBtn().setEnabled(false);
            }
        });

        mpane.getpOSTagging2().getPosBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                for (String s : tokenizedText) {
//                    createThread("pos", s);
//                }
                mpane.getpOSTagging2().getNext4Btn().setEnabled(true);
            }
        });

        mpane.getpOSTagging2().getBack3Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setSelectedIndex(2);
            }
        });

        mpane.getpOSTagging2().getNext4Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setEnabledAt(4, true);
                mpane.getjTabbedPane1().setSelectedIndex(4);
                mpane.getpOSTagging2().getPosBtn().setEnabled(false);
            }
        });

        mpane.getClassifying1().getClassifyBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getClassifying1().getNext5Btn().setEnabled(true);
            }
        });

        mpane.getClassifying1().getBack4Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setSelectedIndex(3);
            }
        });

        mpane.getClassifying1().getNext5Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setEnabledAt(5, true);
                mpane.getjTabbedPane1().setSelectedIndex(5);
                mpane.getClassifying1().getClassifyBtn().setEnabled(false);
            }
        });

        mpane.getVisualizing1().getFinishBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.setVisible(false);
            }
        });

        mpane.getVisualizing1().getBack5Btn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mpane.getjTabbedPane1().setSelectedIndex(4);
            }
        });
    }

    private void disableComponent() {
        mpane.getjTabbedPane1().setEnabledAt(1, false);
        mpane.getjTabbedPane1().setEnabledAt(2, false);
        mpane.getjTabbedPane1().setEnabledAt(3, false);
        mpane.getjTabbedPane1().setEnabledAt(4, false);
        mpane.getjTabbedPane1().setEnabledAt(5, false);
        mpane.getSelectingFiles1().getNext1Btn().setEnabled(false);
        mpane.getFile2Text1().getNext2Btn().setEnabled(false);
        mpane.getTokenizing1().getNext2Btn().setEnabled(false);
        mpane.getpOSTagging2().getNext4Btn().setEnabled(false);
        mpane.getClassifying1().getNext5Btn().setEnabled(false);
    }
}
