package com.itextpdf.jumpstart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.LinkedHashSet;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import org.junit.jupiter.api.Test;

class PdfMergersTest {

    @Test
    void throwExceptionForEmptySetInMultimerge() throws java.io.IOException{
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        assertThrows(IOException.class, () -> PdfMergers.multiMerge(inputPdfs, "./testing/testMultiMerge.pdf"));
    }

    @Test
    void throwExceptionForNonexistantPdfInMultimerge() throws java.io.IOException{
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        inputPdfs.add("./input/9780820601762-1.pdf");
        inputPdfs.add("./input/9780820601762-2.pdf");
        inputPdfs.add("");
        assertThrows(IOException.class, () -> PdfMergers.multiMerge(inputPdfs, "./testing/testMultiMerge.pdf"));
    }

    @Test
    void checkPageNumsForPdfMerge() throws java.io.IOException{
        LinkedHashSet<String> inputPdfs = new LinkedHashSet<>();
        inputPdfs.add("./input/9780820601762-1.pdf");
        inputPdfs.add("./input/9780820601762-2.pdf");
        inputPdfs.add("./input/9780820601762-3.pdf");

        int totalLength = 0;
        for(String pdfPath : inputPdfs){
            PdfDocument pdf = new PdfDocument(new PdfReader(pdfPath));
            totalLength += pdf.getNumberOfPages();
        }
        PdfMergers.multiMerge(inputPdfs, "./testing/testMultiMerge.pdf");
        PdfDocument mergedPDf = new PdfDocument(new PdfReader("./testing/testMultiMerge.pdf"));
        assertEquals(totalLength, mergedPDf.getNumberOfPages());
    }   

}
