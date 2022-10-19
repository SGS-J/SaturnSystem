package com.SGSJ.Saturn.controller.VacancyDetail;

import com.SGSJ.Saturn.config.DataHolder;
import com.SGSJ.Saturn.controller.GenericController;
import com.SGSJ.Saturn.controller.VacancyMain.VacancyProperty;
import com.SGSJ.Saturn.domain.Vacancy.Vacancy;
import com.SGSJ.Saturn.domain.Vacancy.VacancyService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VacancyDetailController extends GenericController {

    @FXML
    private Button editBtn;

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

        vacancy.setVacancyId(vacancyProperty.getId());
        vacancy.setName(name);
        vacancy.setJobOffer(jobOffer);
    }

    @FXML
    void handleTextChanged() {
        vacancy.setName(nameField.getText());
        vacancy.setJobOffer(Integer.valueOf(jobOfferField.getText()));
    }

    @FXML
    void handleEditVacancy(ActionEvent event) {
        Button btnPressed = (Button) event.getSource();
        String btnPressedId = btnPressed.getId();

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
