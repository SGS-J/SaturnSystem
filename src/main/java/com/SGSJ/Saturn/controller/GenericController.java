package com.SGSJ.Saturn.controller;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.security.LoginInfo;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public abstract class GenericController implements Initializable {
    @FXML
    protected Button toApplicantSceneBtn;

    @FXML
    protected Button toVacancySceneBtn;

    @FXML
    protected Button logOutBtn;

    @Override
    public abstract void initialize(URL location, ResourceBundle resources);

    @FXML
    protected void handleSceneChangeButtonPressed(ActionEvent event) throws IOException {
        Button source = (Button) event.getSource();
        String id = source.getId();
        if(id.equalsIgnoreCase("toapplicantscenebtn")) {
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.APPLICANT_MAIN);
        } else {
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.VACANCY_MAIN);
        }
    }

    @FXML
    protected void handleLogOut() throws IOException {
        SaturnSystemApplication.setUserLogged(LoginInfo.NOT_LOGGED);
        SaturnSystemApplication.getStageManager().switchScene(SaturnView.LOG_IN);
    }
}
