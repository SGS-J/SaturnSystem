package com.SGSJ.Saturn.view.controller.ApplicantDetail.config;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import javafx.scene.web.WebView;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class PDFViewerConfiguration {
    public WebView getPDFViewer(String pdfPath) {
        PDFDisplayer displayer = new PDFDisplayer();
        try {
            displayer.loadPDF(new File(pdfPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (WebView) displayer.toNode();
    }
}
