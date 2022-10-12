package com.SGSJ.Saturn;

import com.SGSJ.Saturn.config.SaturnFXMLLoader;
import com.SGSJ.Saturn.config.StageManager;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SaturnSystemApplication extends Application {
    private static StageManager stageManager;
    private final String appTitle = "Saturn";
    private final int HEIGHT = 700;
    private final int WIDTH = 1000;
    private ConfigurableApplicationContext appContext;

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setHeight(HEIGHT);
            primaryStage.setWidth(WIDTH);
            primaryStage.setTitle(appTitle);

            stageManager = new StageManager(primaryStage, new SaturnFXMLLoader(appContext));
            stageManager.switchScene(SaturnView.LOG_IN);
        } catch (IOException exception){
            throw new RuntimeException();
        }
    }

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainAppInitializer.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        appContext = builder.run(args);
    }

    @Override
    public void stop() throws Exception {
        appContext.close();
        Platform.exit();
    }

    public static StageManager getStageManager() {
        return stageManager;
    }
}
