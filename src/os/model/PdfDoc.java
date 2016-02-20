/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class PdfDoc {

    private final String text, name, dir;

    public PdfDoc(String text, String name, String dir) {
        this.text = text;
        this.name = name;
        this.dir = dir;
    }

    public String getText() {
        return this.text;
    }

    public String getName() {
        return this.name;
    }

    String getDir() {
        return this.dir;
    }

    static PdfDoc fromFile(File file) throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, file.getAbsolutePath() + "Not Found");
            Logger.getLogger(PdfDoc.class.getName()).log(Level.SEVERE, null, ex);
        }
        ParseContext pcontext = new ParseContext();

        PDFParser pdfparser = new PDFParser();

        pdfparser.parse(inputstream, handler, metadata, pcontext);
        String text = handler.toString();
        return new PdfDoc(text, file.getName(), file.getAbsolutePath());
    }
}
