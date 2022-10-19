package com.SGSJ.Saturn.controller.ApplicantDetail;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.config.DataHolder;
import com.SGSJ.Saturn.controller.ApplicantMain.UserProperty;
import com.SGSJ.Saturn.controller.GenericController;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.domain.User.UserState;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button rejectBtn;

    @FXML
    private Label secondaryPhoneLabel;

    private DataHolder<UserProperty> userData;
    @Autowired
    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userData = DataHolder.getInstance();
        UserProperty userProperty = userData.getObject();

        nameLabel.setText(userProperty.getName());
        mainPhoneLabel.setText(userProperty.getMainPhone());
        secondaryPhoneLabel.setText(userProperty.getSecondaryPhone());
        emailLabel.setText(userProperty.getEmail());
    }

    @FXML
    synchronized void handleButtonPressed(ActionEvent event) throws IOException {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

        switch (btnPressedId) {
            case "acceptBtn":
                userService.updateState(UserState.ACEPTADO, userData.getObject().getId());
                break;
            case "rejectBtn":
                userService.updateState(UserState.RECHAZADO, userData.getObject().getId());
                break;
            case "deleteBtn":
                userService.deleteById(userData.getObject().getId());
                SaturnSystemApplication.getStageManager().switchScene(SaturnView.APPLICANT_MAIN);
                break;
        };
    }
}
