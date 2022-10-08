package com.SGSJ.Saturn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SaturnMainController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField postField;
    @FXML
    private TextField permissionField;
    @FXML
    private Button submitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onSave(){

    }
}
