package com.SGSJ.Saturn.controller.Register;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.controller.Register.util.RegisterVerificator;
import com.SGSJ.Saturn.domain.Employee.Employee;
import com.SGSJ.Saturn.domain.Employee.EmployeeService;
import com.SGSJ.Saturn.exceptions.InvalidRegisterDataException;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class RegisterController implements Initializable {
    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField postField;

    @FXML
    private Button registerBtn;

    @FXML
    private Hyperlink toLogInLink;

    @FXML
    private TextField usernameField;

    @FXML
    private Label passwordValidLabel;

    private String confirmationPassword;
    @Autowired
    private Employee employee;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employee.setName(usernameField.getText());
        employee.setPassword(passwordField.getText());
        employee.setPost(postField.getText());
        confirmationPassword = confirmPasswordField.getText();
        passwordField.focusedProperty().addListener(((observable, oldValue, fieldFocused) -> {
            passwordValidLabel.setVisible(fieldFocused);
        }));
    }

    @FXML
    void handleTextChanged() {
        employee.setName(usernameField.getText());
        employee.setPassword(passwordField.getText());
        employee.setPost(postField.getText());
        confirmationPassword = confirmPasswordField.getText();
    }

    @FXML
    synchronized void handleRegister() throws IOException {
        try {
            RegisterVerificator.verifyEmptyFields(this.employee);
            RegisterVerificator.verifyPassword(employee.getPassword(), this.confirmationPassword);
            employeeService.add(employee);
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.LOG_IN);
        } catch(InvalidRegisterDataException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(e.getDialogErrorTitle());
            alert.setHeaderText(null);
            alert.setContentText(e.getPasswordErrorMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void handleSwitchLogIn() throws IOException {
        SaturnSystemApplication.getStageManager().switchScene(SaturnView.LOG_IN);
    }

}
