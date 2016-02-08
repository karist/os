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
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.apache.commons.io.FilenameUtils;
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
    JTextArea displayedText;
    static List<String> textList = new ArrayList<>();

    public Controller() {
    }
    
    public Controller(JScrollPane jScrollPane1, JButton browseBtn, JTextArea selectedTxt) {
        spane = jScrollPane1;
        button = browseBtn;
        displayedText = selectedTxt;
    }

    public void browse() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf", "PDF");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.showOpenDialog(null);

        File[] files = fileChooser.getSelectedFiles();
        try {
            //        if (checkSelection(files) == true) {
//            singleThread(files);
            multiThread(files);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayedText.append("You've selected " + files.length + " PDF file(s).\n");
//        } else {
//            JOptionPane.showMessageDialog(fileChooser, "Please select PDF file(s) only");
//        }
    }

    public void creatthread(String uri) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Date date = new Date();
                    String tname = Thread.currentThread().getName();
                    System.out.println(date + "\t" + tname + "\t" + uri);
                    fileProcessing(uri);
                } catch (IOException | SAXException | TikaException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    public void fileProcessing(String uri) throws IOException, FileNotFoundException, SAXException, TikaException {
        String textFile = pdfParse(uri);
        String textTokenized = manualTokenize(textFile);
//        String textTokenized = Tokenize(textFile);
        String textAfterPOSTagging = POSTag(textTokenized);
        createTxt(FilenameUtils.getBaseName(uri), textAfterPOSTagging);
        createTxt("text prop", uri + "\t: " + textFile.length());
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

    public static String POSTag(String a) throws IOException {
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

    public String tokenize(String teks) throws InvalidFormatException, IOException {
        InputStream is = new FileInputStream("en-token.bin");

        TokenizerModel model = new TokenizerModel(is);

        Tokenizer tokenizer = new TokenizerME(model);

        String tokens[] = tokenizer.tokenize(teks);
        String result = "";

        for (String a : tokens) {
            result = result + " " + a;
        }

        is.close();

        return result;
    }

    public String manualTokenize(String teks) {
        String result = "";
        StringTokenizer defaultTokenizer = new StringTokenizer(teks);
        String teks1 = "";
        while (defaultTokenizer.hasMoreTokens()) {
            teks1 += defaultTokenizer.nextToken() + "\n";
        }
        StringTokenizer multiTokenizer = new StringTokenizer(teks1, ":/.,-+=~!@#$%^&*()_?/><|';\"");
        System.out.println("Total number of token found: " + multiTokenizer.countTokens());
        while (multiTokenizer.hasMoreTokens()) {
            System.out.println(multiTokenizer.nextToken());
        }
        return result;
    }

    private boolean checkSelection(File[] files) {
        boolean check = true;
        while (check == true) {
            for (File file : files) {
                String ext = FilenameUtils.getExtension(file.getName());
                if (ext.equals("pdf") == false) {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    public void singleThread(File[] files) throws IOException, FileNotFoundException, SAXException, TikaException {
        for (File file : files) {
            displayedText.append(file.getName() + "\n");
            fileProcessing(file.getAbsolutePath());
        }
    }

    public void multiThread(File[] files) {
        for (File f : files) {
            displayedText.append(f.getName() + "\n");
            creatthread(f.getAbsolutePath());
        }
    }
}
