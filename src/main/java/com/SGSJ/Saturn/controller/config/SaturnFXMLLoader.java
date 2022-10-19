package com.SGSJ.Saturn.controller.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SaturnFXMLLoader {
    private final ApplicationContext appContext;

    @Autowired
    public SaturnFXMLLoader(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    public Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(appContext::getBean);
        loader.setLocation(new ClassPathResource(fxmlPath).getURL());
        return loader.load();
    }
}
