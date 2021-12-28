package com.itextpdf.jumpstart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import org.junit.jupiter.api.Test;

class PdfUtilitiesTest {
    
    @Test
    void deleteFileDoesNotThrowException() throws java.io.IOException {
        String testFilePath = "./testing/testfile.txt";
        File testFile = new File(testFilePath);
        testFile.createNewFile();
        PdfUtilities.deleteFile(testFilePath);
        assertFalse(testFile.exists());
    }

    @Test
    void deleteNonexistentFileThrowException() throws java.io.IOException {
        assertThrows(java.io.IOException.class, () -> PdfUtilities.deleteFile("./testing/testdeletenotexist.txt"));
    }

    @Test
    void createBlankDocumentLengthZero() throws FileNotFoundException, IndexOutOfBoundsException {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> PdfUtilities.createBlankDocumentWithLength(0, "./testing/testblankdoc.pdf"));
    }

    @Test
    void createBlankDocumentLengthNegative() throws FileNotFoundException, IndexOutOfBoundsException {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> PdfUtilities.createBlankDocumentWithLength(-5, "./testing/testblankdoc.pdf"));
    }

    @Test
    void createBlankDocumentLengthPositive() throws IndexOutOfBoundsException, IOException {
        int documentLength = 5;
        PdfUtilities.createBlankDocumentWithLength(documentLength, "./testing/testblankdoc.pdf");
        PdfDocument pdf = new PdfDocument(new PdfReader("./testing/testblankdoc.pdf"));
        assertEquals(documentLength, pdf.getNumberOfPages());
    }

}
