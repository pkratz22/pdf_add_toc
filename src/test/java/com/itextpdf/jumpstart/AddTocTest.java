package com.itextpdf.jumpstart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import org.junit.jupiter.api.Test;


class AddTocTest {
    
    @Test
    void writeTocInvalidPdfSource() throws java.io.IOException {
        Toc toc = new Toc("./testing/toc.csv");
        assertThrows(java.io.IOException.class, () -> AddToc.writeToc("./testing/fakepdfsource.pdf", toc, "./testing/testfailtocpdfcreation.pdf"));
    }

    @Test
    void writeTocBlankToc() throws java.io.IOException {
        Toc toc = new Toc("./testing/toc2.csv");
        assertThrows(java.io.IOException.class, () -> AddToc.writeToc("./testing/fakepdfsource.pdf", toc, "./testing/testfailtocpdfcreation.pdf"));
    }

    @Test
    void writeValidToc() throws java.io.IOException {
        Toc toc = new Toc("./testing/toc.csv");
        AddToc.writeToc("./testing/testCreateToc.pdf", toc, "./testing/testCreateTocOutput.pdf");
        PdfDocument pdfInput = new PdfDocument(new PdfReader("./testing/testCreateToc.pdf"));
        PdfDocument pdfOutput = new PdfDocument(new PdfReader("./testing/testCreateTocOutput.pdf"));
        assertEquals(pdfInput.getNumberOfPages() + toc.tocPageLength, pdfOutput.getNumberOfPages());
    }
}
