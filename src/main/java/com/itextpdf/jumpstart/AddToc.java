package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddToc {

    public static PdfDocument writeToc(String pdfSource, Toc toc, String output) throws IOException{
        try (PdfReader reader = new PdfReader(pdfSource)){
            try (PdfWriter writer = new PdfWriter(output)){
                PdfDocument pdf = new PdfDocument(reader, writer);
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

    //I have set up this main function to perform the tasks I need
    //In this case, it is to merge three PDFs and insert a Table of Contents from a CSV into a page number
    public static void main(String [] args) throws IOException {
        JFileChooser csvChooser = new JFileChooser();
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV for Table of Contents", "csv");
        csvChooser.setFileFilter(csvFilter);
        csvChooser.showOpenDialog(null);

        JFileChooser pdfChooser = new JFileChooser();
        FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("PDF getting new table of contents", "pdf");
        pdfChooser.setFileFilter(pdfFilter);
        pdfChooser.showOpenDialog(null);

        JFileChooser outputChooser = new JFileChooser();
        outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        outputChooser.showSaveDialog(null);

        Toc toc = new Toc(csvChooser.getSelectedFile().getAbsolutePath());
        writeToc(pdfChooser.getSelectedFile().getAbsolutePath(), toc, outputChooser.getCurrentDirectory() + "/merged.pdf");
    }
}
