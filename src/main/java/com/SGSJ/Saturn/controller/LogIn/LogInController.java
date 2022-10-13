package com.SGSJ.Saturn.controller.LogIn;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.config.StageManager;
import com.SGSJ.Saturn.domain.Employee.Employee;
import com.SGSJ.Saturn.domain.Employee.EmployeeService;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LogInController implements Initializable {
    @FXML
    private Button logInBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink toRegisterLink;

    @FXML
    private TextField usernameField;

    private String employeeName;
    private String employeePassword;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeName = usernameField.getText();
        employeePassword = passwordField.getText();
    }

    @FXML
    void handleTextChanged() {
        employeeName = usernameField.getText();
        employeePassword = passwordField.getText();
    }

    @FXML
    void handleLogIn() throws IOException {
        Employee employeeLogged = employeeService.logIn(employeeName, employeePassword);
        if(employeeLogged != null) {
           SaturnSystemApplication.getStageManager().switchScene(SaturnView.APPLICANT_MAIN);
        }
    }

    @FXML
    void handleSwitchRegister() throws IOException {
        SaturnSystemApplication.getStageManager().switchScene(SaturnView.REGISTER);
    }

}
