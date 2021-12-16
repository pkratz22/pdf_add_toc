package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.text.Chunk;
import com.itextpdf.layout.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
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

    public Toc(String tocFilePath) throws IOException {
        this.tocFilePath = tocFilePath;
        this.tocFileContents = readToc(this.tocFilePath);
        this.tocLength = tocFileContents.size();
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

    public PdfDocument writeStandaloneTocPdf(List<String[]> tocFileContents) throws FileNotFoundException {
        PdfDocument pdf = new PdfDocument(new PdfWriter("./output/toc.pdf"));
        Document document = new Document(pdf);

        Paragraph p;
        for(String[] element : tocFileContents){
            p = new Paragraph();
            p.add(element[0]);
            p.add(element[1]);
            document.add(p);
        }
        pdf.close();
        return pdf;
    }

}
