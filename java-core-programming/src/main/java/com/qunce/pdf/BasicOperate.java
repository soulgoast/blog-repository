package com.qunce.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class BasicOperate {

    @Test
    public void createPDF() throws IOException {
        PDDocument pdDocument = new PDDocument();
        pdDocument.save("D:\\pdf\\my_doc.pdf");
        pdDocument.close();
    }

    @Test
    public void createPage() throws IOException {
        PDDocument pdDocument = new PDDocument();
        for (int i = 0; i < 10; i++) {
            PDPage page = new PDPage();
            pdDocument.addPage(page);
        }

        pdDocument.save("D:\\pdf\\my_doc.pdf");
        pdDocument.close();
    }

    @Test
    public void readPDF() throws IOException {
        File file = new File("D:\\pdf\\info.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        System.out.println(text);
        document.close();
    }
}
