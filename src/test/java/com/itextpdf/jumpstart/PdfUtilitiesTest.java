package com.itextpdf.jumpstart;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

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

}
