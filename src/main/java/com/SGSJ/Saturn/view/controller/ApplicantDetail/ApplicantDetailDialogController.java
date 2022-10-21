package com.SGSJ.Saturn.view.controller.ApplicantDetail;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.view.config.DataHolder;
import com.SGSJ.Saturn.view.controller.ApplicantMain.UserProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ApplicantDetailDialogController implements Initializable {
    @FXML
    private Button cancelBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label messageLabel;

    private static String type = "EN_ESPERA";
    private static boolean okClicked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText(setLabelMessage());
    }

    private String setLabelMessage() {
        String msg = "";

        switch (type) {
            case "ACEPTADO" -> {
                msg = """
                        Aceptar al postulante provocará que no pueda ser rechazado ni eliminado.

                        ¿Continuar?""";
            }

            case "RECHAZADO" -> {
                msg = """
                        Rechazar al postulante provocará que no pueda ser aceptado.
                        
                        ¿Continuar?
                        """;
            }

            case "EN_ESPERA" -> {
                msg = """
                        El postulante aún no ha sido rechazado o aceptado.
                        
                        ¿Eliminar de todas formas?
                        """;
            }

            case "DELETE" -> {
                msg = "¿Eliminar postulante?";
            }

        }

        return msg;
    }

    @FXML
    void handleButtonPressed(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();
        String btnClickedId = btnClicked.getId();

        okClicked = btnClickedId.equals("confirmBtn");
        if(okClicked && !type.equals("DELETE")) {
            DataHolder<UserProperty> boundData = DataHolder.getInstance();
            boundData.getObject().setState(type);
        }

        SaturnSystemApplication.getStageManager().closeDialogModal();
    }

    public static void setType(String type) {
        ApplicantDetailDialogController.type = type;
    }

    public static String getType() {
        return type;
    }

    public static boolean isOkClicked() {
        return okClicked;
    }
}
