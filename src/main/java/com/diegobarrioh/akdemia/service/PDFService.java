package com.diegobarrioh.akdemia.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PDFService {

    @Value("${storage.location}")
    public String baseLocation;

    public String parseTextFromPages(File pdfFile, int startPage, int endPage) {
        PDDocument document = null;
        String parsedText = null;
        try {
             document = PDDocument.load(pdfFile);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);

            parsedText = pdfStripper.getText(document);

            System.out.println(parsedText);

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return parsedText;


    }
}
