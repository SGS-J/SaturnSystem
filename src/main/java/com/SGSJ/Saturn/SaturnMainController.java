package com.SGSJ.Saturn;

import com.SGSJ.Saturn.domain.Employee.Employee;
import com.SGSJ.Saturn.domain.Employee.EmployeeService;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employee.setName(nameField.getText());
        employee.setPassword(passwordField.getText());
        employee.setPost(postField.getText());
        employee.setPermission(permissionField.getText());
    }

    @FXML
    public void handleTextChanged() {
        employee.setName(nameField.getText());
        employee.setPassword(passwordField.getText());
        employee.setPost(postField.getText());
        employee.setPermission(permissionField.getText());
    }

    @FXML
    public void handleSubmit(){
        employeeService.add(employee);
    }
}
