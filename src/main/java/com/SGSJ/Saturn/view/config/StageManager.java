package com.SGSJ.Saturn.view.config;

import com.SGSJ.Saturn.view.SaturnView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class StageManager {
    private final Stage primaryStage;
    private Stage dialogStage;
    private static SaturnFXMLLoader FXMLLoader;

    public StageManager(Stage primaryStage, SaturnFXMLLoader FXMLLoader) {
        this.primaryStage = primaryStage;
        StageManager.FXMLLoader = FXMLLoader;
    }

    public void showDialogModal(Node node, String title) {
        Scene scene;
        if(node.getScene() == null) {
            scene = new Scene((Parent) node);
        } else {
            scene = node.getScene();
        }
        showDialogModal(scene, title);
    }

    public void showDialogModal(final SaturnView view, String title) throws IOException {
        Parent rootNode = FXMLLoader.loadFXML(view.getSaturnViewPath());
        Scene scene = new Scene(rootNode);
        showDialogModal(scene, title);
    }

    public void closeDialogModal() {
        dialogStage.close();
    }

    public void switchScene(final SaturnView view) throws IOException {
        Parent rootNode = FXMLLoader.loadFXML(view.getSaturnViewPath());
        show(rootNode);
    }

    private void showDialogModal(Scene scene, String title) {
        dialogStage = new Stage();
        try {
            dialogStage.getIcons().add(new Image(new ClassPathResource("/icon/saturn.png").getURL().toExternalForm()));
        } catch (IOException ignored) {
        }
        dialogStage.setTitle(title);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.primaryStage);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    private void show(final Parent rootNode) {
       Scene scene = prepareScene(rootNode);
       primaryStage.setScene(scene);
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
