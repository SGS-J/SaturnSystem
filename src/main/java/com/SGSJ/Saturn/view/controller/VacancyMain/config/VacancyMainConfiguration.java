package com.SGSJ.Saturn.view.controller.VacancyMain.config;

import com.SGSJ.Saturn.view.controller.VacancyMain.VacancyMainController;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VacancyMainConfiguration {
    public void configureSlider(VacancyMainController controller) {
        Slider jobOfferSlider = controller.getJobOfferSlider();
        Label amountOfferLabel = controller.getAmountOfferLabel();
        TableView vacancyTable = controller.getVacancyTable();


        jobOfferSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            amountOfferLabel.setText(String.valueOf(newValue.intValue()));
            vacancyTable.setItems(controller.getVacancyData().filtered(vacancyProperty -> {
                Integer jobOffer = vacancyProperty.getJobOffer();
                return jobOffer >= newValue.intValue();
            }));
        });
    }

    public void configureTableView(VacancyMainController controller) {
        controller.getIdColumn().setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        controller.getNameColumn().setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        controller.getJobOfferColumn().setCellValueFactory(cellData -> cellData.getValue().jobOfferProperty().asObject());

        controller.getVacancyTable().setItems(controller.getVacancyData());
    }
}
