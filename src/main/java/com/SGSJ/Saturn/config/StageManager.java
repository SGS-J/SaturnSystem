package com.SGSJ.Saturn.config;

import com.SGSJ.Saturn.view.SaturnView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class StageManager {
    private final Stage primaryStage;
    private static SaturnFXMLLoader FXMLLoader;

    public StageManager(Stage primaryStage, SaturnFXMLLoader FXMLLoader) {
        this.primaryStage = primaryStage;
        this.FXMLLoader = FXMLLoader;
    }

    public void switchScene(final SaturnView view) throws IOException {
        Parent rootNode = FXMLLoader.loadFXML(view.getSaturnViewPath());
        show(rootNode);
    }

    private void show(final Parent rootNode) {
       Scene scene = prepareScene(rootNode);

       primaryStage.setScene(scene);
       primaryStage.centerOnScreen();

       primaryStage.show();
    }

    private Scene prepareScene(Parent rootNode) {
       Scene scene = primaryStage.getScene();

       if(scene == null) {
           scene = new Scene(rootNode);
       }
        scene.setRoot(rootNode);
        return scene;
    }
}
