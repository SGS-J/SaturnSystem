package com.SGSJ.Saturn.view.controller.ApplicantDetail;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.view.config.DataHolder;
import com.SGSJ.Saturn.view.controller.ApplicantDetail.config.PDFViewerConfiguration;
import com.SGSJ.Saturn.view.controller.ApplicantMain.UserProperty;
import com.SGSJ.Saturn.view.controller.GenericController;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.domain.User.UserState;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ApplicantDetailController extends GenericController {
    @FXML
    private Button acceptBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label emailLabel;

    @FXML
    private Label mainPhoneLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button viewCvBtn;

    @FXML
    private Button rejectBtn;

    @FXML
    private Label secondaryPhoneLabel;

    @FXML
    private WebView pdfViewerWrapper;

    private DataHolder<UserProperty> userData;
    @Autowired
    private UserService userService;
    @Autowired
    private PDFViewerConfiguration pdfViewerConfiguration;
    private WebView webEnginePdf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userData = DataHolder.getInstance();
        UserProperty userProperty = userData.getObject();

        idLabel.setText(String.valueOf(userProperty.getId()));
        nameLabel.setText(userProperty.getName());
        mainPhoneLabel.setText(userProperty.getMainPhone());
        secondaryPhoneLabel.setText(userProperty.getSecondaryPhone());
        emailLabel.setText(userProperty.getEmail());
        webEnginePdf = pdfViewerConfiguration.getPDFViewer(userProperty.getPathToCV());
    }

    @FXML
    synchronized void handleButtonPressed(ActionEvent event) throws IOException {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

        if(btnPressedId.equals("viewCvBtn")) {
            SaturnSystemApplication.getStageManager().showDialogModal(webEnginePdf, "Hoja de Vida");
        } else {
            switch (btnPressedId) {
                case "acceptBtn" -> userService.updateState(UserState.ACEPTADO, userData.getObject().getId());
                case "rejectBtn" -> userService.updateState(UserState.RECHAZADO, userData.getObject().getId());
                case "deleteBtn" -> userService.deleteById(userData.getObject().getId());
            }
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.APPLICANT_MAIN);
        }


    }
}
