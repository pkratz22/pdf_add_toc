package com.itextpdf.jumpstart;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Toc {
    String tocFilePath;
    List<String[]> tocFileContents;

    public Toc(String tocFilePath) throws IOException {
        this.tocFilePath = tocFilePath;
        this.tocFileContents = readToc(this.tocFilePath);
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

    public static void main(String[] args) throws IOException {
        Toc testToc = new Toc("./input/toc.csv");
        System.out.println(testToc.tocFileContents);
    }

}
