package com.SGSJ.Saturn;

import com.SGSJ.Saturn.view.controller.config.SaturnFXMLLoader;
import com.SGSJ.Saturn.view.controller.config.StageManager;
import com.SGSJ.Saturn.security.LoginInfo;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SaturnSystemApplication extends Application {
    private static StageManager stageManager;
    private static LoginInfo userLogged = LoginInfo.NOT_LOGGED;
    private final String APP_TITLE = "Saturn";
    private final int HEIGHT = 700;
    private final int WIDTH = 1000;
    private ConfigurableApplicationContext appContext;

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setHeight(HEIGHT);
            primaryStage.setWidth(WIDTH);
            primaryStage.setTitle(APP_TITLE);

            stageManager = new StageManager(primaryStage, new SaturnFXMLLoader(appContext));

            if(isUserLogged()) {
                stageManager.switchScene(SaturnView.APPLICANT_MAIN);
            } else {
                stageManager.switchScene(SaturnView.LOG_IN);
            }
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

    public static LoginInfo getUserLogged() {
        return userLogged;
    }

    public static boolean isUserLogged() {
        return userLogged.equals(LoginInfo.LOGGED);
    }

    public static void setUserLogged(LoginInfo userLogged) {
        SaturnSystemApplication.userLogged = userLogged;
    }
}
