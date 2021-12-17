package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.*;

import java.io.*;
import java.util.LinkedHashSet;

import com.itextpdf.jumpstart.Toc;
import com.itextpdf.jumpstart.pdfMergers;
import com.itextpdf.jumpstart.pdfUtilities;

public class AddToc {

    private static final String DEST = "./output/";
    private static final String FILE1 = "./input/9780820601762-1.pdf";
    private static final String FILE2 = "./input/9780820601762-2.pdf";
    private static final String FILE3 = "./input/9780820601762-3.pdf";
    private static final String TOCCSVFILE = "./input/toc.csv";
    private static final String TOCPDFFILE = "./output/toc.pdf";
    private static final String OUTPUT = "./output/output.pdf";

    private static final int INSERTLOCATION = 5;

    public static void main(String [] args) throws IOException {
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        inputPdfs.add(FILE1);
        inputPdfs.add(FILE2);
        inputPdfs.add(FILE3);

        pdfMergers.multiMerge(inputPdfs,  "./input/merged.pdf");

        Toc toc = new Toc(TOCCSVFILE);
        toc.writeStandaloneTocPdf(toc.tocFileContents);

        pdfMergers.mergeAtLocation(DEST+"merged.pdf", TOCPDFFILE, INSERTLOCATION, OUTPUT);

    }
}
