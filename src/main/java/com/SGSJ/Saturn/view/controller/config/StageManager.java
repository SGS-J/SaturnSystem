package com.SGSJ.Saturn.view.controller.config;

import com.SGSJ.Saturn.view.SaturnView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private final Stage primaryStage;
    private Stage dialogStage;
    private static SaturnFXMLLoader FXMLLoader;

    public StageManager(Stage primaryStage, SaturnFXMLLoader FXMLLoader) {
        this.primaryStage = primaryStage;
        StageManager.FXMLLoader = FXMLLoader;
    }

    public void showDialogModal(final SaturnView view, String title) throws IOException {
        Parent rootNode = FXMLLoader.loadFXML(view.getSaturnViewPath());
        Scene scene = new Scene(rootNode);
        dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.primaryStage);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    public void closeDialogModal() {
        dialogStage.close();
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
