package com.SGSJ.Saturn.view.controller.ApplicantDetail;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.view.config.DataHolder;
import com.SGSJ.Saturn.view.controller.ApplicantDetail.config.ApplicantDetailConfiguration;
import com.SGSJ.Saturn.view.controller.ApplicantMain.UserProperty;
import com.SGSJ.Saturn.view.controller.GenericController;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.domain.User.UserState;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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
    private Label stateLabel;

    private DataHolder<UserProperty> userData;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicantDetailConfiguration initConfiguration;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userData = DataHolder.getInstance();
        UserProperty userProperty = userData.getObject();

        idLabel.setText(String.valueOf(userProperty.getId()));
        nameLabel.setText(userProperty.getName());
        mainPhoneLabel.setText(userProperty.getMainPhone());
        secondaryPhoneLabel.setText(userProperty.getSecondaryPhone());
        emailLabel.setText(userProperty.getEmail());
        stateLabel.setText(userProperty.getState());

        initConfiguration.configApplicantButtons(this);
    }

    @FXML
    synchronized void handleButtonPressed(ActionEvent event) throws IOException {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

        if(btnPressedId.equals("viewCvBtn")) {
            File file = new File(userData.getObject().getPathToCV());
            SaturnSystemApplication.getAppHostServices().showDocument(file.getAbsolutePath());
        } else {
            switch (btnPressedId) {
                case "acceptBtn" -> ApplicantDetailDialogController.setType(UserState.ACEPTADO.name());
                case "rejectBtn" -> ApplicantDetailDialogController.setType(UserState.RECHAZADO.name());
                case "deleteBtn" -> {
                    String state = userData.getObject().getState();
                    String dialogState = state.equals(UserState.EN_ESPERA.name()) ? state : "DELETE";
                    ApplicantDetailDialogController.setType(dialogState);
                }
            }
            SaturnSystemApplication.getStageManager().showDialogModal(SaturnView.APPLICANT_DIALOG_UPDATE_STATE, "Actualizar Estado.");
            if(ApplicantDetailDialogController.isOkClicked()) {
                String dialogType = ApplicantDetailDialogController.getType();
                Long id = userData.getObject().getId();
                if(dialogType.equals("DELETE")) {
                    String pathFile = userData.getObject().getPathToCV();
                    File file = new File(pathFile);
                    FileUtils.delete(file);
                    userService.deleteById(id);
                } else {
                    String state = userData.getObject().getState();
                    userService.updateState(UserState.valueOf(state), id);
                }
                SaturnSystemApplication.getStageManager().switchScene(SaturnView.APPLICANT_MAIN);
            };
        }
    }

    public DataHolder<UserProperty> getUserData() {
        return userData;
    }

    public void setUserData(DataHolder<UserProperty> userData) {
        this.userData = userData;
    }

    public Button getRejectBtn() {
        return rejectBtn;
    }

    public void setRejectBtn(Button rejectBtn) {
        this.rejectBtn = rejectBtn;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }

    public void setAcceptBtn(Button acceptBtn) {
        this.acceptBtn = acceptBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }
}
