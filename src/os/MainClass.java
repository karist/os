/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import java.util.ArrayList;

/**
 *
 * @author nugraha
 */
class PdfDoc {

    private final String text, name, dir;

    PdfDoc(String text, String name, String dir) {
        this.text = text;
        this.name = name;
        this.dir = dir;
    }

    String getText() {
        return this.text;
    }

    String getName() {
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

class PdfList {

    private final List<PdfDoc> documents;

    PdfList(List<PdfDoc> documents) {
        this.documents = documents;
    }

    List<PdfDoc> getDocuments() {
        return this.documents;
    }

    static PdfList fromDirectory(File dir) throws IOException, SAXException, TikaException {
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
}

class PdfFileTask extends RecursiveAction {

    PdfList pList;
    static List<String> textList = new ArrayList<>();
    int index;

    PdfFileTask(PdfList pList, int index) {
        super();
        this.pList = pList;
        this.index = index;
    }

    @Override
    protected void compute() {
        int number = pList.getDocuments().size();
        if (number == 1) {
            process("src/os/txtmulti/");
        } else if (number > 1) {
            int mid = number / 2;
            PdfList x = new PdfList(pList.getDocuments().subList(0, mid));
            PdfList y = new PdfList(pList.getDocuments().subList(mid, number));
            invokeAll(new PdfFileTask(x, 0), new PdfFileTask(y, 0));
        }
    }

    public void process(String dir) {
        PdfDoc pdoc = pList.getDocuments().get(index);
        try {
            String text = tillPOSTag(pdoc.getText());
            System.out.println(text);
            createTxt(pdoc.getName(), text, dir);
        } catch (IOException ex) {
            Logger.getLogger(PdfFileTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String tillPOSTag(String a) throws IOException {
        POSModel model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);

        ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(a));

        perfMon.start();
        String line, result = "";
        while ((line = lineStream.read()) != null) {

            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
            String[] tags = tagger.tag(whitespaceTokenizerLine);

            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
            result = result + sample.toString();

            perfMon.incrementCounter();
        }
        perfMon.stopAndPrintFinalResult();
        textList.add(result);
        return result;
    }

    public void createTxt(String fileName, String text, String dir) {
        File f = new File(dir + fileName + ".txt");
        try {
            if (f.exists() == false) {
//                JOptionPane.showMessageDialog(null, "Create new file succeed");
                f.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(new FileWriter(f, false))) {
                out.append(text).println();
                out.close();
//            displayTxt.append(fileName + " txt created\n");
            }

        } catch (HeadlessException | IOException e) {
            System.out.println(e.getMessage());

        }
//        JOptionPane.showMessageDialog(null, "File Saved");
    }
}

public class MainClass {

    int numberOfProcessors = Runtime.getRuntime().availableProcessors();
    private final ForkJoinPool forkJoinPool = new ForkJoinPool(numberOfProcessors);
    static long startTime, stopTime;

    MainClass() {
    }

    public void onSingleThread(PdfList pList) {
        List p = pList.getDocuments();
        int number = pList.getDocuments().size();
        for (int i = 0; i < number; i++) {
            PdfFileTask x = new PdfFileTask(pList, i);
            x.process("src/os/txtsingle/");
        }
    }

    public void inParallel(PdfList pList) {
        forkJoinPool.invoke(new PdfFileTask(pList, 0));
    }

    public static void main(String[] args) throws IOException, SAXException, TikaException {
//        File folder = new File("E:\\arsendi\\Documents\\NetBeansProjects\\OS\\pdf");
//        File[] listOfFiles = folder.listFiles();
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                PdfDoc pdoc = fromFile(file);
//                System.out.println(file.getName());
//            }
//        }
        PdfList pList = PdfList.fromDirectory(new File("E:\\arsendi\\Documents\\NetBeansProjects\\OS\\pdf"));
        MainClass mc = new MainClass();
        System.out.println("Number of Processors: " + mc.numberOfProcessors);
        int number = pList.getDocuments().size();

        startTime = System.currentTimeMillis();
        mc.onSingleThread(pList);
        stopTime = System.currentTimeMillis();
        long singleThreadTimes = (stopTime - startTime);

        startTime = System.currentTimeMillis();
        mc.inParallel(pList);
        stopTime = System.currentTimeMillis();
        long multiThreadTimes = (stopTime - startTime);

        System.out.println("Single thread process took " + singleThreadTimes + "ms");
        System.out.println("Multithread process took " + multiThreadTimes + "ms");
    }

}
