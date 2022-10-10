package com.SGSJ.Saturn.config;

import com.SGSJ.Saturn.view.SaturnView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private final Stage primaryStage;
    private final SaturnFXMLLoader FXMLLoader;

    public StageManager(Stage primaryStage, SaturnFXMLLoader FXMLLoader) {
        this.primaryStage = primaryStage;
        this.FXMLLoader = FXMLLoader;
    }

    public void switchScene(final SaturnView view, String title) throws IOException {
        Parent rootNode = FXMLLoader.loadFXML(view.getSaturnViewPath());
        this.show(rootNode, title);
    }

    private void show(final Parent rootNode, String title) {
       Scene scene = prepareScene(rootNode);

       primaryStage.setTitle(title);
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
