package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Toc {

    String tocFilePath;
    List<String[]> tocFileContents;
    int tocLength;
    int tocPageLength;

    public Toc(String tocFilePath) throws IOException {
        this.tocFilePath = tocFilePath;
        this.tocFileContents = readToc(this.tocFilePath);
        this.tocLength = tocFileContents.size();
        this.tocPageLength = getTocPageLength(this.tocFileContents);
    }

    public List<String[]> readToc(String tocFilePath) throws IOException {
        List<String[]> r = null;
        try (CSVReader reader = new CSVReader(new FileReader(tocFilePath))) {
            r = reader.readAll();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int getTocPageLength(List<String[]> tocFileContents) throws FileNotFoundException {
        if(tocFileContents.isEmpty()){
            return 0;
        }
        else{
            PdfDocument pdf = new PdfDocument(new PdfWriter("./output/toc.pdf"));
        
            try (Document document = new Document(pdf)){
                Paragraph p;
                for(String[] element : tocFileContents){
                    p = new Paragraph();
        
                    p.add(element[0]);
                    p.add(element[1]);
                    document.add(p);
        
                }
                int pageLength = pdf.getNumberOfPages();
                pdf.close();
                return pageLength;
            }
        }
    }

}
