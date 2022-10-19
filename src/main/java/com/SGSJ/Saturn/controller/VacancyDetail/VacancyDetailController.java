package com.SGSJ.Saturn.controller.VacancyDetail;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.controller.config.DataHolder;
import com.SGSJ.Saturn.controller.GenericController;
import com.SGSJ.Saturn.controller.VacancyMain.VacancyProperty;
import com.SGSJ.Saturn.domain.Vacancy.Vacancy;
import com.SGSJ.Saturn.domain.Vacancy.VacancyService;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    private Button dismissBtn;

    @FXML
    private TextField jobOfferField;

    @FXML
    private TextField nameField;

    private DataHolder<VacancyProperty> vacancyData;
    private boolean isEditingFields = false;
    private String[] buttonTexts = {"Editar", "Aceptar"};
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
            if(!text.isEmpty() && text.matches("[0-9]*")) return change;
            return null;
        }));

        vacancy.setVacancyId(vacancyProperty.getId());
        vacancy.setName(name);
        vacancy.setJobOffer(jobOffer);
    }

    @FXML
    void handleTextChanged(KeyEvent event) {
        vacancy.setName(nameField.getText());
        vacancy.setJobOffer(Integer.valueOf(jobOfferField.getText()));
    }

    @FXML
    void handleButtonPressed(ActionEvent event) throws IOException {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

        if(btnPressedId.equals("deleteVacancyBtn")) {
            vacancyService.deleteById(vacancy.getVacancyId());
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.VACANCY_MAIN);
        } else {
            handleEditButtonPressed(btnPressedId);
        }

    }

    private void handleEditButtonPressed(String btnPressedId) {
        isEditingFields = !isEditingFields;
        nameField.setEditable(isEditingFields);
        jobOfferField.setEditable(isEditingFields);
        editBtn.setText(isEditingFields ? buttonTexts[1] :buttonTexts[0]);
        dismissBtn.setVisible(isEditingFields);

        if(!isEditingFields) {
            if(btnPressedId.equals("editBtn")) {
                vacancyService.updateById(vacancy, vacancy.getVacancyId());
                vacancyData.getObject().setName(vacancy.getName());
                vacancyData.getObject().setJobOffer(vacancy.getJobOffer());
                isEditingFields = false;
            }

            if(btnPressedId.equals("dismissBtn")) {
                nameField.setText(vacancyData.getObject().getName());
                jobOfferField.setText(Integer.toString(vacancyData.getObject().getJobOffer()));
                isEditingFields = false;
            }

        }
    }

}
