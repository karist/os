/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import os.model.PdfList;

/**
 *
 * @author nugraha
 */
public class ForkJoinCon {

    int numberOfProcessors = Runtime.getRuntime().availableProcessors();
    private final ForkJoinPool forkJoinPool = new ForkJoinPool(numberOfProcessors);
    static long startTime1, stopTime1, startTime2, stopTime2, multiThreadTimes, singleThreadTimes;

    public ForkJoinCon() {
    }

    public void onSingleThread(PdfList pList) throws IOException {
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
    
    public void go() throws IOException, SAXException, TikaException{
        PdfList pList = PdfList.fromDirectory(new File("E:\\arsendi\\Documents\\NetBeansProjects\\OS\\pdf"));
        ForkJoinCon fjc = new ForkJoinCon();
        System.out.println("Number of Processors: " + fjc.numberOfProcessors);
        int number = pList.getDocuments().size();

        startTime1 = System.currentTimeMillis();
        fjc.onSingleThread(pList);
        stopTime1 = System.currentTimeMillis();
        singleThreadTimes = (stopTime1 - startTime1);

        startTime2 = System.currentTimeMillis();
        fjc.inParallel(pList);
        stopTime2 = System.currentTimeMillis();
        multiThreadTimes = (stopTime2 - startTime2);
        
        System.out.println("\nSingle thread process took " + fjc.getSingleThreadTimes() + "ms");
        System.out.println("\nMultithread process took " + fjc.getMultiThreadTimes() + "ms");
    }

    public int getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public static long getMultiThreadTimes() {
        return multiThreadTimes;
    }

    public static long getSingleThreadTimes() {
        return singleThreadTimes;
    }
    
    
}
