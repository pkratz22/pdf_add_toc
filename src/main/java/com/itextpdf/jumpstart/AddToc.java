package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;
import java.util.LinkedHashSet;

public class AddToc {

    private static final String FILE1 = "./input/9780820601762-1.pdf";
    private static final String FILE2 = "./input/9780820601762-2.pdf";
    private static final String FILE3 = "./input/9780820601762-3.pdf";
    private static final String TOCCSVFILE = "./input/toc.csv";
    private static final String OUTPUT = "./output/output.pdf";
    private static final String TEMPPATH1 = "./input/temp.pdf";
    private static final String TEMPPATH2 = "./input/temp2.pdf";

    public static PdfDocument writeToc(String pdfSource, Toc toc, String output) throws IOException{
        try (PdfDocument pdf = new PdfDocument(new PdfReader(pdfSource), new PdfWriter(output))){
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

    //I have set up this main function to perform the tasks I need
    //In this case, it is to merge three PDFs and insert a Table of Contents from a CSV into a page number
    public static void main(String [] args) throws IOException {
        //First merge three volumes into one PDF
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        inputPdfs.add(FILE1);
        inputPdfs.add(FILE2);
        inputPdfs.add(FILE3);
        PdfMergers.multiMerge(inputPdfs,  "./input/merged.pdf");

        //Next, read Table of Contents from CSV
        Toc toc = new Toc(TOCCSVFILE);

        //Add blank pages that will contain the table of contents
        PdfUtilities.createBlankDocumentWithLength(toc.tocPageLength, TEMPPATH1);
        LinkedHashSet<String> mergeWithBlankToc = new LinkedHashSet<>();
        mergeWithBlankToc.add(TEMPPATH1);
        mergeWithBlankToc.add("./input/merged.pdf");
        PdfMergers.multiMerge(mergeWithBlankToc, TEMPPATH2);

        //Write the table of contents
        writeToc(TEMPPATH2, toc, OUTPUT);

        //Delete superfluous files (later won't be necessary once actions are done in memory
        PdfUtilities.deleteFile(TEMPPATH1);
        PdfUtilities.deleteFile(TEMPPATH2);
        PdfUtilities.deleteFile("./output/unorderedPdfWithToc.pdf");
        PdfUtilities.deleteFile("./output/toc.pdf");
    }
}
