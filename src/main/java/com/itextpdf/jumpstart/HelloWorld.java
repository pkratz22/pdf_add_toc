package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.utils.PdfMerger;


import java.io.*;
import java.util.LinkedHashSet;

public class HelloWorld {

    public static final String DEST = "./output/";
    public static final String FILE1 = "./input/9780820601762-1.pdf";
    public static final String FILE2 = "./input/9780820601762-2.pdf";
    public static final String FILE3 = "./input/9780820601762-3.pdf";

    public static void main(String [] args) throws IOException{
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        inputPdfs.add(FILE1);
        inputPdfs.add(FILE2);
        inputPdfs.add(FILE3);
        new HelloWorld().mergePdfs(inputPdfs);
    }

    public void mergePdfs(LinkedHashSet<String> inputPdfs) throws IOException{
        // change so that it uses accepts a list of pdfs and merges them instead of one by one

        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST + "merged.pdf"));
        PdfMerger merger = new PdfMerger(pdf);

        for (String pdfName : inputPdfs) {
            PdfDocument sourcePdf = new PdfDocument(new PdfReader(pdfName));
            sourcePdf.getCatalog().getPdfObject().remove(PdfName.Outlines);
            merger.merge(sourcePdf, 1, sourcePdf.getNumberOfPages());
            sourcePdf.close();
        }

        pdf.close();

    }

}
