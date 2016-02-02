/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author nugraha
 */
public class Controller {

    JScrollPane spane;
    JButton button;
    JTextArea teks;

    public Controller(JScrollPane jScrollPane1, JButton browseBtn, JTextArea selectedTxt) {
        spane = jScrollPane1;
        button = browseBtn;
        teks = selectedTxt;
    }

    public void browse() {
        JFileChooser chooser = new JFileChooser(".");
        FileFilter pdfFilter = new ExtensionFileFilter(null, new String[]{"PDF"});
        chooser.addChoosableFileFilter(pdfFilter);
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(null);

        File[] files = chooser.getSelectedFiles();
        String fileName;
        for (File f : files) {
            teks.append(f.getName() + "\n");
            creatthread(files, f.getName());
        }
    }

    public void creatthread(File[] files, String fileName) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Date date = new Date();
                    String tname = Thread.currentThread().getName();
                    System.out.println(date + "\t" +tname +"\t" +fileName);
                    pdfParse(fileName);
                } catch (IOException | SAXException | TikaException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    public void pdfParse(String fileName) throws FileNotFoundException, IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(fileName));
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);

        //getting the content of the document
        System.out.println("Contents of the PDF :" + handler.toString());

        //getting metadata of the document
        System.out.println("Metadata of the PDF:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + " : " + metadata.get(name));
        }
        createTxt(fileName, textPOSTagging(handler.toString()));
        System.out.println(handler.toString().length());
    }

    public String textPOSTagging(String a) {
        MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");
        String tagged = tagger.tagString(a);
        return tagged;
    }

    public void createTxt(String fileName, String text) {
        File f = new File("src/os/txt/" + fileName + ".txt");
        try {
            if (f.exists() == false) {
                JOptionPane.showMessageDialog(null, "Create new file succeed");
                f.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(f, false));
            out.append(text).println();
            out.close();

        } catch (HeadlessException | IOException e) {
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "File Saved");
    }

    class ExtensionFileFilter extends FileFilter {

        String description;
        String extensions[];

        public ExtensionFileFilter(String description, String extension) {
            this(description, new String[]{extension});
        }

        public ExtensionFileFilter(String description, String extensions[]) {
            if (description == null) {
                this.description = extensions[0] + " files";
            } else {
                this.description = description;
            }
            this.extensions = (String[]) extensions.clone();
            toLower(this.extensions);
        }

        private void toLower(String array[]) {
            for (int i = 0, n = array.length; i < n; i++) {
                array[i] = array[i].toLowerCase();
            }
        }

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            } else {
                String path = file.getAbsolutePath().toLowerCase();
                for (int i = 0, n = extensions.length; i < n; i++) {
                    String extension = extensions[i];
                    if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
