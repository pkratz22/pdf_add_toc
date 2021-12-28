package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.property.AreaBreakType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfUtilities {

    private PdfUtilities(){}

    public static void deleteFile(String filePath) throws IOException{
        Path path = Paths.get(filePath);
        Files.delete(path);
    }

    public static PdfDocument createBlankDocumentWithLength(int length, String filepath) throws FileNotFoundException, IndexOutOfBoundsException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(filepath));
        Document document = new Document(pdf);

        for(int i=0; i<length; i++){
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }
        pdf.removePage(1);
        pdf.close();
        document.close();
        return pdf;
    }
}
