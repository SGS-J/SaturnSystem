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
import java.util.logging.Level;

public class SaturnSystemApplication extends Application {
    private static StageManager stageManager;
    private static LoginInfo userLogged = LoginInfo.NOT_LOGGED;
    private static HostServices hostServices;
    private final String APP_TITLE = "Saturn";
    private final int HEIGHT = 700;
    private final int WIDTH = 1000;
    private ConfigurableApplicationContext appContext;

    private LoggerConfig loggerConfig = LoggerConfig.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        hostServices = getHostServices();

        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setTitle(APP_TITLE);

        try {
            primaryStage.getIcons().add(new Image(new ClassPathResource("/icon/saturn.png").getURL().toExternalForm()));
        } catch (IOException ignored) {
        }

        try {
            stageManager = new StageManager(primaryStage, new SaturnFXMLLoader(appContext));

            if (isUserLogged()) {
                stageManager.switchScene(SaturnView.APPLICANT_MAIN);
            } else {
                stageManager.switchScene(SaturnView.LOG_IN);
            }
        } catch (IOException e) {
            loggerConfig.getLogger().log(Level.SEVERE, loggerConfig.getLogMessage(e.getMessage()));
            stop();
        }
    }

    @Override
    public void init() throws Exception{
        try {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(MainAppInitializer.class);
            String[] args = getParameters().getRaw().toArray(String[]::new);
            appContext = builder.run(args);
        } catch(Exception e) {
            loggerConfig.getLogger().log(Level.SEVERE, loggerConfig.getLogMessage(e.getMessage()));
            stop();
        }
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
