package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.utils.PdfMerger;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.*;

public class HelloWorld {

    public static final String DEST = "/Users/peterkratz/IdeaProjects/pdf_add_toc/output/";
    public static final String FILE1 = "/Users/peterkratz/IdeaProjects/pdf_add_toc/input/9780820601762-1.pdf";
    public static final String FILE2 = "/Users/peterkratz/IdeaProjects/pdf_add_toc/input/9780820601762-2.pdf";
    public static final String FILE3 = "/Users/peterkratz/IdeaProjects/pdf_add_toc/input/9780820601762-3.pdf";

    public static void main(String [] args) throws IOException{
        new HelloWorld().mergePdfs();
    }

    public void mergePdfs() throws IOException{

        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST + "merged.pdf"));
        PdfMerger merger = new PdfMerger(pdf);

        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(FILE1));
        firstSourcePdf.getCatalog().getPdfObject().remove(PdfName.Outlines);
        merger.merge(firstSourcePdf, 1, firstSourcePdf.getNumberOfPages());

        PdfDocument secondSourcePdf = new PdfDocument(new PdfReader(FILE2));
        secondSourcePdf.getCatalog().getPdfObject().remove(PdfName.Outlines);
        merger.merge(secondSourcePdf, 1, secondSourcePdf.getNumberOfPages());

        PdfDocument thirdSourcePdf = new PdfDocument(new PdfReader(FILE3));
        thirdSourcePdf.getCatalog().getPdfObject().remove(PdfName.Outlines);
        merger.merge(thirdSourcePdf, 1, thirdSourcePdf.getNumberOfPages());

        firstSourcePdf.close();
        secondSourcePdf.close();
        thirdSourcePdf.close();
        pdf.close();

    }

    public void readPdf(String input_loc) throws IOException {

        File fileConn = new File(FILE1);
        InputStream inp = new FileInputStream(fileConn);
        PdfReader reader = new PdfReader(inp);
    }

    public void createPdf(String destination) throws IOException{
        // Initialize PDF Writer
        FileOutputStream fos = new FileOutputStream(DEST);
        PdfWriter writer = new PdfWriter(fos);

        // Initialize PDF Document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize Document
        Document document = new Document(pdf);

        // Add paragraph to document
        document.add(new Paragraph("Hello World!"));

        // Close document
        document.close();

    }

}
