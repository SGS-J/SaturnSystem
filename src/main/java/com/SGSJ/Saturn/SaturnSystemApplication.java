package com.SGSJ.Saturn;

import com.SGSJ.Saturn.view.config.SaturnFXMLLoader;
import com.SGSJ.Saturn.view.config.StageManager;
import com.SGSJ.Saturn.security.LoginInfo;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class SaturnSystemApplication extends Application {
    private static StageManager stageManager;
    private static LoginInfo userLogged = LoginInfo.NOT_LOGGED;
    private static HostServices hostServices;
    private final String APP_TITLE = "Saturn";
    private final int HEIGHT = 700;
    private final int WIDTH = 1000;
    private ConfigurableApplicationContext appContext;

    @Override
    public void start(Stage primaryStage) {
        hostServices = getHostServices();

        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setTitle(APP_TITLE);

        try {
            primaryStage.getIcons().add(new Image(new ClassPathResource("/icon/saturn.png").getURL().toExternalForm()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
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
        String[] args = getParameters().getRaw().toArray(String[]::new);
        appContext = builder.run(args);
    }

    @Override
    public void stop() throws Exception {
        appContext.close();
        Platform.exit();
    }

    public static HostServices getAppHostServices() {
        return hostServices;
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
