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

    public static PdfDocument mergeAtLocationWithLinks(String mainPdfSource, Toc toc, String output) throws IOException{
        try (PdfDocument pdf = new PdfDocument(new PdfReader(mainPdfSource), new PdfWriter(output))){
            final Document document = new Document(pdf);
            Paragraph p;
            PdfArray pdfArr;
    
            for(String[] element : toc.tocFileContents){
                pdfArr = new PdfArray();
                pdfArr.add(document.getPdfDocument().getPage(Integer.parseInt(element[1]) + toc.tocPageLength).getPdfObject());
                pdfArr.add(PdfName.Fit);
                p = new Paragraph(new Link(element[0], PdfAction.createGoTo(PdfDestination.makeDestination(pdfArr))));
                document.add(p);
            }
            document.close();
            return pdf;
        }
        
    }

}
