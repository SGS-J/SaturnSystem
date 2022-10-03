package com.SGSJ.Saturn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<SaturnUIApplication.StageReadyEvent> {
    private ApplicationContext appContext;
    private String appTitle;

    public StageInitializer(ApplicationContext appContext, @Value("${spring.application.ui.title}") String appTitle) {
        this.appContext = appContext;
        this.appTitle = appTitle;
    }

    @Override
    public void onApplicationEvent(SaturnUIApplication.StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = (Stage) stageReadyEvent.getSource();
            FXMLLoader loader = new FXMLLoader(new ClassPathResource("/scene.fxml").getURL());
            loader.setControllerFactory(this.appContext::getBean);

            Parent rootNode = loader.load();
            stage.setScene(new Scene(rootNode, 800, 800));
            stage.setTitle(this.appTitle);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Object getBean(Class<?> aClass) {
        return this.appContext.getBean(aClass);
    }
}
