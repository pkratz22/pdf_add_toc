package com.itextpdf.jumpstart;


import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.element.Link;

import java.io.IOException;
import java.util.Set;

public final class PdfMergers {

    private PdfMergers(){}

    public static void multiMerge(Set<String> inputPdfs, String output) throws com.itextpdf.io.IOException, PdfException, IOException {
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

}
