package com.SGSJ.Saturn.view.controller.ApplicantDetail.config;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import javafx.scene.control.Alert;
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se encontró el archivo pdf.");
            alert.showAndWait();
        }
        return (WebView) displayer.toNode();
    }
}
