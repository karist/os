/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import os.model.PdfDoc;
import os.model.PdfList;

/**
 *
 * @author nugraha
 */
public class PdfFileTask extends RecursiveAction {

    PdfList pList;
    static List<String> textList = new ArrayList<>();
    int index;
    Controller con;

    PdfFileTask(PdfList pList, int index) {
        super();
        this.pList = pList;
        this.index = index;
        con = new Controller();
    }

    @Override
    protected void compute() {
        int number = pList.getDocuments().size();
        if (number == 1) {
            try {
                process("src/os/txtmulti/");
            } catch (IOException ex) {
                Logger.getLogger(PdfFileTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (number > 1) {
            int mid = number / 2;
            PdfList x = new PdfList(pList.getDocuments().subList(0, mid));
            PdfList y = new PdfList(pList.getDocuments().subList(mid, number));
            invokeAll(new PdfFileTask(x, 0), new PdfFileTask(y, 0));
        }
    }

    public void process(String dir) throws IOException {
        PdfDoc pdoc = pList.getDocuments().get(index);
        String text = Controller.POSTag(pdoc.getText());
        System.out.println(text);
        con.createTxt(pdoc.getName(), text, dir);
    }

}
