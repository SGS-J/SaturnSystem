package com.SGSJ.Saturn.controller.ApplicantMain.config;

import com.SGSJ.Saturn.controller.ApplicantMain.ApplicantMainController;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicantMainConfiguration {
    public void configureTableView(ApplicantMainController controller) {
        controller.getIdColumn().setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        controller.getNameColumn().setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        controller.getEmailColumn().setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        controller.getMainPhoneColumn().setCellValueFactory(cellData -> cellData.getValue().mainPhoneProperty());
        controller.getSecondaryPhoneColumn().setCellValueFactory(cellData -> cellData.getValue().secondaryPhoneProperty());
        controller.getStateColumn().setCellValueFactory(cellData -> cellData.getValue().stateProperty());

        controller.getApplicantTable().setItems(controller.getUserData());
    }
}
