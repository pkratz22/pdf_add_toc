package com.itextpdf.jumpstart;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;

public final class pdfMergers {

    public static void multiMerge(LinkedHashSet<String> inputPdfs, String output) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(output));
        PdfMerger merger = new PdfMerger(pdf);

        for (String pdfName : inputPdfs) {
            PdfDocument sourcePdf = new PdfDocument(new PdfReader(pdfName));
            sourcePdf.getCatalog().getPdfObject().remove(PdfName.Outlines);
            merger.merge(sourcePdf, 1, sourcePdf.getNumberOfPages());
            sourcePdf.close();
        }
        pdf.close();
    }

    public static void mergeAtLocation(String mainPdfSource, String insertedPdfSource, int insertLocation, String output) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(output));
        PdfMerger merger = new PdfMerger(pdf);

        PdfDocument mainPdf = new PdfDocument(new PdfReader(mainPdfSource));
        PdfDocument insertedPdf = new PdfDocument(new PdfReader(insertedPdfSource));

        merger.merge(mainPdf, 1, insertLocation);
        merger.merge(insertedPdf, 1, insertedPdf.getNumberOfPages());
        merger.merge(mainPdf, insertLocation + 1, mainPdf.getNumberOfPages());

        mainPdf.close();
        insertedPdf.close();
        pdf.close();
    }

}
