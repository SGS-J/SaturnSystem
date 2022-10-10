package com.SGSJ.Saturn;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class SaturnSystemApplication extends Application {
    private ConfigurableApplicationContext appContext;

    @Override
    public void start(Stage primaryStage) throws Exception {
        appContext.publishEvent(new StageReadyEvent(primaryStage));
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

    /*
      Create stage ready event class
      launching the UI on another thread.
     */
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) this.getSource());
        }
    }
}
