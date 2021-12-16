package com.itextpdf.jumpstart;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.List;

import com.itextpdf.jumpstart.Toc;

public class AddToc {

    private static final String DEST = "./output/";
    private static final String FILE1 = "./input/9780820601762-1.pdf";
    private static final String FILE2 = "./input/9780820601762-2.pdf";
    private static final String FILE3 = "./input/9780820601762-3.pdf";
    private static final String TOCFILE = "./input/toc.csv";

    private static final int INSERTLOCATION = 5;

    public static void main(String [] args) throws IOException {
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        inputPdfs.add(FILE1);
        inputPdfs.add(FILE2);
        inputPdfs.add(FILE3);

        String mergedFileName = DEST + "merged.pdf";
        String outputFileName = DEST + "output.pdf";

        Toc toc = new Toc(TOCFILE);

        new AddToc().createTocPdf(mergedFileName, outputFileName, toc.tocFileContents, INSERTLOCATION);
    }

    private void mergePdfs(LinkedHashSet<String> inputPdfs, String outputName) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST + outputName));
        PdfMerger merger = new PdfMerger(pdf);

        for (String pdfName : inputPdfs) {
            PdfDocument sourcePdf = new PdfDocument(new PdfReader(pdfName));
            sourcePdf.getCatalog().getPdfObject().remove(PdfName.Outlines);
            merger.merge(sourcePdf, 1, sourcePdf.getNumberOfPages());
            sourcePdf.close();
        }
        pdf.close();
    }

    private int getTocLength(List<String[]> toc) {
        return 1;
    }

    private void createTocPdf(String inputPdfName, String outputFileName, List<String[]> toc, int insertLocation) throws IOException {
        PdfDocument inputPdf = new PdfDocument(new PdfReader(inputPdfName), new PdfWriter(outputFileName));
        inputPdf.addNewPage(insertLocation);
        Document document = new Document(inputPdf);
        Rectangle pageSize;
        PdfCanvas canvas;
        int docLength = inputPdf.getNumberOfPages();

        for(int i = insertLocation; i <= docLength; i++){
            System.out.println("Testing");
        }
    }
}
