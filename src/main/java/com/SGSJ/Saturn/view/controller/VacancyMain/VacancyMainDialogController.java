package com.SGSJ.Saturn.view.controller.VacancyMain;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.view.config.DataHolder;
import com.SGSJ.Saturn.domain.Vacancy.Vacancy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VacancyMainDialogController implements Initializable{
    @FXML
    private Button acceptBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField offerField;

    @FXML
    private Label warningMsgLabel;

    @Autowired
    private Vacancy vacancy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vacancy.setName(nameField.getText());
        vacancy.setJobOffer(0);

        nameField.focusedProperty().addListener((observable, oldValue, isFocused) -> {
            warningMsgLabel.setText("");
        });

        offerField.focusedProperty().addListener((observable, oldValue, isFocused) -> {
            warningMsgLabel.setText("");
        });

        offerField.setText("0");
        offerField.setTextFormatter(new TextFormatter<String>(change -> {
            String text = change.getControlNewText();
            if(text.matches("[0-9]*")) return change;
            return null;
        }));
    }

    @FXML
    void handleTextChanged() {
        String offerText = offerField.getText().isBlank() ? "0" : offerField.getText();
        vacancy.setJobOffer(Integer.valueOf(offerText));
        vacancy.setName(nameField.getText());
    }

    @FXML
    void handleButtonPressed(ActionEvent event) {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

        if (btnPressedId.equals("acceptBtn")) {
            if(nameField.getText().isBlank() || offerField.getText().isBlank()) {
                warningMsgLabel.setText("Los campos no pueden estar vacíos.");
            } else {
                DataHolder<Vacancy> bindData = DataHolder.getInstance();
                bindData.setObject(vacancy);
                SaturnSystemApplication.getStageManager().closeDialogModal();
            }
        }

        if(btnPressedId.equals("cancelBtn")) {
            SaturnSystemApplication.getStageManager().closeDialogModal();
        }
    }
}
