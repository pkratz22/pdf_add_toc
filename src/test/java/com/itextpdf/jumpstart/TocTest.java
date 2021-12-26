package com.itextpdf.jumpstart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TocTest {
    
    @Test
    public void testValidToc() throws java.io.IOException{
        Toc tocTest = new Toc("./input/toc.csv");
        assertEquals(3, tocTest.tocPageLength);
    }
}
