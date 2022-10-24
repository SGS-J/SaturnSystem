package com.SGSJ.Saturn.view.controller.ApplicantDetail.config;

import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.aspectj.util.FileUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Base64;

@Configuration
public class PDFViewerConfiguration {
    public void getPDFViewer(String pdfPath, WebView webView) {
        WebEngine engine = webView.getEngine();

        String htmlPath = "";
        String cssPath = "";

        try {
            htmlPath = new ClassPathResource("pdfengine/web/viewer.html").getURI().toString();
            cssPath = new ClassPathResource("pdfengine/web/viewer.css").getURI().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        engine.setUserStyleSheetLocation(cssPath);
        engine.setJavaScriptEnabled(true);
        engine.load(htmlPath);

        engine.getLoadWorker()
                .stateProperty()
                .addListener((obs, oldV, newV) -> {
                    if (Worker.State.SUCCEEDED == newV) {
                        try {
                            InputStream is = new FileInputStream(pdfPath);
                            byte[] bytes = FileUtil.readAsByteArray(is);
                            // Base64 from java.util
                            String base64 = Base64.getEncoder().encodeToString(bytes);

                            engine.executeScript("openFileFromBase64('" + base64 + "')");

                        } catch (Exception | Error ex) {
                            ex.printStackTrace();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setTitle("Error");
                            alert.setContentText("No se encontró el archivo pdf.");
                            alert.showAndWait();
                        }
                    }
                });
    }

}
