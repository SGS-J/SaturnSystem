package com.SGSJ.Saturn.controller.VacancyMain;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.config.DataHolder;
import com.SGSJ.Saturn.controller.GenericController;
import com.SGSJ.Saturn.controller.VacancyMain.config.VacancyMainConfiguration;
import com.SGSJ.Saturn.domain.Vacancy.VacancyService;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VacancyMainController extends GenericController {

    @FXML
    private TableView<VacancyProperty> vacancyTable;

    @FXML
    private TableColumn<VacancyProperty, Long> idColumn;

    @FXML
    private TableColumn<VacancyProperty, Integer> jobOfferColumn;

    @FXML
    private Slider jobOfferSlider;

    @FXML
    private TableColumn<VacancyProperty, String> nameColumn;

    @FXML
    private Label amountOfferLabel;

    private ObservableList<VacancyProperty> vacancyData = FXCollections.observableArrayList();
    @Autowired
    private VacancyMainConfiguration initConfiguration;
    @Autowired
    private VacancyService vacancyService;

    @Override
    synchronized public void initialize(URL location, ResourceBundle resources) {
        vacancyData.clear();
        vacancyService.getAll().stream().forEach(vacancy -> {
            vacancyData.add(new VacancyProperty(vacancy));
        });

        initConfiguration.configureSlider(this);
        initConfiguration.configureTableView(this);
    }

    @FXML
    void handleClickCell(MouseEvent event) throws IOException {
        if(event.getClickCount() == 2) {
            DataHolder<VacancyProperty> bindData = DataHolder.getInstance();
            VacancyProperty selectedItem = vacancyTable.getSelectionModel().getSelectedItem();
            bindData.setObject(selectedItem);
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.VACANCY_DETAIL);
        }
    }

    public TableView<VacancyProperty> getVacancyTable() {
        return vacancyTable;
    }

    public void setVacancyTable(TableView<VacancyProperty> vacancyTable) {
        this.vacancyTable = vacancyTable;
    }

    public TableColumn<VacancyProperty, Long> getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(TableColumn<VacancyProperty, Long> idColumn) {
        this.idColumn = idColumn;
    }

    public TableColumn<VacancyProperty, Integer> getJobOfferColumn() {
        return jobOfferColumn;
    }

    public void setJobOfferColumn(TableColumn<VacancyProperty, Integer> jobOfferColumn) {
        this.jobOfferColumn = jobOfferColumn;
    }

    public Slider getJobOfferSlider() {
        return jobOfferSlider;
    }

    public void setJobOfferSlider(Slider jobOfferSlider) {
        this.jobOfferSlider = jobOfferSlider;
    }

    public TableColumn<VacancyProperty, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<VacancyProperty, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public Label getAmountOfferLabel() {
        return amountOfferLabel;
    }

    public void setAmountOfferLabel(Label amountOfferLabel) {
        this.amountOfferLabel = amountOfferLabel;
    }

    public ObservableList<VacancyProperty> getVacancyData() {
        return vacancyData;
    }

    public void setVacancyData(ObservableList<VacancyProperty> vacancyData) {
        this.vacancyData = vacancyData;
    }

}
