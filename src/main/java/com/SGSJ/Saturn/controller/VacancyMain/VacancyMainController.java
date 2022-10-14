package com.SGSJ.Saturn.controller.VacancyMain;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.config.DataHolder;
import com.SGSJ.Saturn.controller.SidePanelController;
import com.SGSJ.Saturn.domain.Vacancy.VacancyService;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class VacancyMainController extends SidePanelController {

    @FXML
    private TableView<VacancyProperty> vacancyTable;

    @FXML
    private TableColumn<VacancyProperty, Long> idColumn;

    @FXML
    private TableColumn<VacancyProperty, Integer> jobOfferColumn;

    @FXML
    private Spinner<?> jobOfferSpinner;

    @FXML
    private TableColumn<VacancyProperty, String> nameColumn;

    private ObservableList<VacancyProperty> vacancyData = FXCollections.observableArrayList();
    @Autowired
    private VacancyService vacancyService;

    @Override
    synchronized public void initialize(URL location, ResourceBundle resources) {
        vacancyData.clear();
        vacancyService.getAll().stream().forEach(vacancy -> {
            vacancyData.add(new VacancyProperty(vacancy));
        });

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        jobOfferColumn.setCellValueFactory(cellData -> cellData.getValue().jobOfferProperty().asObject());

        vacancyTable.setItems(vacancyData);
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
}
