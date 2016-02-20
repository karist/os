/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author nugraha
 */
public class PdfList {

    private final List<PdfDoc> documents;

    public PdfList(List<PdfDoc> documents) {
        this.documents = documents;
    }

    /**
     *
     * @return
     */
    public List<PdfDoc> getDocuments() {
        return this.documents;
    }

    public static PdfList fromDirectory(File dir) throws IOException, SAXException, TikaException {
        List<PdfDoc> documents = new LinkedList<>();
        for (File entry : dir.listFiles()) {
            documents.add(PdfDoc.fromFile(entry));
        }
        return new PdfList(documents);
    }

    void print() {
        for (PdfDoc s : documents) {
            System.out.println(s.getName());;
        }
    }
    
    public String getNames(){
        String result = "";
        for (PdfDoc s : documents) {
            result += s.getName() + "\n";
        }
        return result;
    }
}
