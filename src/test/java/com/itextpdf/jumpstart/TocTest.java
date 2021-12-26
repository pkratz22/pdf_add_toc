package com.itextpdf.jumpstart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TocTest {
    
    @Test
    void returnPositivePageLengthForNonblankToc() throws java.io.IOException{
        Toc tocTest = new Toc("./input/toc.csv");
        assertEquals(3, tocTest.tocPageLength);
    }

    @Test
    void returnZeroPageLengthForBlankToc() throws java.io.IOException{
        Toc tocTest = new Toc("./input/toc2.csv");
        assertEquals(1, tocTest.tocPageLength);
    }

    @Test
    void throwExceptionForInvalidTocFileString() throws java.io.IOException{
        assertThrows(IOException.class, () -> new Toc(""));
    }

}
