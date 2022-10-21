package com.SGSJ.Saturn.view.controller.VacancyDetail;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.view.config.DataHolder;
import com.SGSJ.Saturn.view.controller.GenericController;
import com.SGSJ.Saturn.view.controller.VacancyMain.VacancyProperty;
import com.SGSJ.Saturn.domain.Vacancy.Vacancy;
import com.SGSJ.Saturn.domain.Vacancy.VacancyService;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VacancyDetailController extends GenericController {

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteVacancyBtn;

    @FXML
    private Button acceptBtn;

    @FXML
    private Button dismissBtn;

    @FXML
    private TextField jobOfferField;

    @FXML
    private TextField nameField;

    @FXML
    private Label warningMsgLabel;

    private DataHolder<VacancyProperty> vacancyData;
    private boolean emptyFields = false;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private Vacancy vacancy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vacancyData = DataHolder.getInstance();
        VacancyProperty vacancyProperty = vacancyData.getObject();
        String name = vacancyProperty.getName();
        Integer jobOffer = vacancyProperty.getJobOffer();

        nameField.setText(name);
        jobOfferField.setText(Integer.toString(jobOffer));
        jobOfferField.setTextFormatter(new TextFormatter<String>(change -> {
            String text = change.getControlNewText();
            if(text.matches("[0-9]*")) return change;
            return null;
        }));

        vacancy.setVacancyId(vacancyProperty.getId());
        vacancy.setName(name);
        vacancy.setJobOffer(jobOffer);
    }

    @FXML
    void handleTextChanged(KeyEvent event) {
        String offerText = jobOfferField.getText().isBlank() ? "0" :
                jobOfferField.getText();
        vacancy.setJobOffer(Integer.valueOf(offerText));
        vacancy.setName(nameField.getText());

        emptyFields = jobOfferField.getText().isBlank() || nameField.getText().isBlank();
        warningMsgLabel.setText(emptyFields ? "Los campos no puede estar vacíos" : "");
    }

    @FXML
    void handleButtonPressed(ActionEvent event) throws IOException {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

        if(btnPressedId.equals("deleteVacancyBtn")) {
            vacancyService.deleteById(vacancy.getVacancyId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vacante eliminada con exito");
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.showAndWait();
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.VACANCY_MAIN);
        } else {
            handleEditButtonPressed(btnPressedId);
        }

    }

    private void handleEditButtonPressed(String btnPressedId) {
        setEditingFields(true);
        if(btnPressedId.equals("acceptBtn") && !emptyFields) {
            vacancyService.updateById(vacancy, vacancy.getVacancyId());
            vacancyData.getObject().setName(vacancy.getName());
            vacancyData.getObject().setJobOffer(vacancy.getJobOffer());
            setEditingFields(false);
        }

        if(btnPressedId.equals("dismissBtn")) {
            nameField.setText(vacancyData.getObject().getName());
            jobOfferField.setText(Integer.toString(vacancyData.getObject().getJobOffer()));
            setEditingFields(false);
        }
    }

    private void setEditingFields(boolean isEditingFields) {
        nameField.setEditable(isEditingFields);
        jobOfferField.setEditable(isEditingFields);
        editBtn.setVisible(!isEditingFields);
        acceptBtn.setVisible(isEditingFields);
        dismissBtn.setVisible(isEditingFields);
        if(!warningMsgLabel.getText().isEmpty() &&
                !isEditingFields) warningMsgLabel.setText("");
    }

}
