package com.itextpdf.jumpstart;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.IOException;

public class pdfUtilities {
    public static PdfDocument getPdfFromFilepath(String pdfName) throws IOException {
        return new PdfDocument(new PdfReader(pdfName));
    }
}
