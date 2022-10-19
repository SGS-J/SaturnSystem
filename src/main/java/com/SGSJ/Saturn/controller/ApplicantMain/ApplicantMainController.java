package com.SGSJ.Saturn.controller.ApplicantMain;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.config.DataHolder;
import com.SGSJ.Saturn.controller.GenericController;
import com.SGSJ.Saturn.controller.ApplicantMain.config.ApplicantMainConfiguration;
import com.SGSJ.Saturn.domain.User.UserService;
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
import java.util.ArrayList;
import java.util.ResourceBundle;


@Controller
public class ApplicantMainController extends GenericController {
    @FXML
    private RadioButton aceptadoRadioBtn;

    @FXML
    private TableColumn<UserProperty, String> emailColumn;

    @FXML
    private RadioButton enEsperaRadioBtn;

    @FXML
    private TableColumn<UserProperty, Long> idColumn;

    @FXML
    private TableColumn<UserProperty, String> mainPhoneColumn;

    @FXML
    private TableColumn<UserProperty, String> nameColumn;

    @FXML
    private RadioButton rechazadoRadioBtn;

    @FXML
    private TableColumn<UserProperty, String> secondaryPhoneColumn;

    @FXML
    private TableColumn<UserProperty, String> stateColumn;

    @FXML
    private TableView<UserProperty> applicantTable;


    private ObservableList<UserProperty> userData = FXCollections.observableArrayList();

    @Autowired
    private ApplicantMainConfiguration initConfiguration;
    @Autowired
    private UserService userService;

    @Override
    synchronized public void initialize(URL location, ResourceBundle resources) {
        userData.clear();
        userService.getAll().stream().forEach(user -> {
            userData.add(new UserProperty(user));
        });

        initConfiguration.configureTableView(this);
    }

    @FXML
    void handleTogglePressed() {
        ArrayList<UserProperty> filterList = new ArrayList<>();

        if(aceptadoRadioBtn.isSelected()) {
             filterList.addAll(userData.filtered(userProperty -> userProperty.getState().equals("ACEPTADO")));
        }

        if(rechazadoRadioBtn.isSelected()) {
            filterList.addAll(userData.filtered(userProperty -> userProperty.getState().equals("RECHAZADO")));
        }

        if(enEsperaRadioBtn.isSelected()) {
            filterList.addAll(userData.filtered(userProperty -> userProperty.getState().equals("EN_ESPERA")));
        }

        applicantTable.setItems(FXCollections.observableArrayList(filterList));
    }

    @FXML
    void handleClickCell(MouseEvent event) throws IOException {
        if(event.getClickCount() == 2) {
            DataHolder<UserProperty> bindData = DataHolder.getInstance();
            UserProperty selectedItem = applicantTable.getSelectionModel().getSelectedItem();
            bindData.setObject(selectedItem);
            SaturnSystemApplication.getStageManager().switchScene(SaturnView.APPLICANT_DETAIL);
        }
    }

    public ObservableList<UserProperty> getUserData() {
        return userData;
    }

    public void setUserData(ObservableList<UserProperty> userData) {
        this.userData = userData;
    }

    public RadioButton getAceptadoRadioBtn() {
        return aceptadoRadioBtn;
    }

    public void setAceptadoRadioBtn(RadioButton aceptadoRadioBtn) {
        this.aceptadoRadioBtn = aceptadoRadioBtn;
    }

    public TableColumn<UserProperty, String> getEmailColumn() {
        return emailColumn;
    }

    public void setEmailColumn(TableColumn<UserProperty, String> emailColumn) {
        this.emailColumn = emailColumn;
    }

    public RadioButton getEnEsperaRadioBtn() {
        return enEsperaRadioBtn;
    }

    public void setEnEsperaRadioBtn(RadioButton enEsperaRadioBtn) {
        this.enEsperaRadioBtn = enEsperaRadioBtn;
    }

    public TableColumn<UserProperty, Long> getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(TableColumn<UserProperty, Long> idColumn) {
        this.idColumn = idColumn;
    }

    public TableColumn<UserProperty, String> getMainPhoneColumn() {
        return mainPhoneColumn;
    }

    public void setMainPhoneColumn(TableColumn<UserProperty, String> mainPhoneColumn) {
        this.mainPhoneColumn = mainPhoneColumn;
    }

    public TableColumn<UserProperty, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<UserProperty, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public RadioButton getRechazadoRadioBtn() {
        return rechazadoRadioBtn;
    }

    public void setRechazadoRadioBtn(RadioButton rechazadoRadioBtn) {
        this.rechazadoRadioBtn = rechazadoRadioBtn;
    }

    public TableColumn<UserProperty, String> getSecondaryPhoneColumn() {
        return secondaryPhoneColumn;
    }

    public void setSecondaryPhoneColumn(TableColumn<UserProperty, String> secondaryPhoneColumn) {
        this.secondaryPhoneColumn = secondaryPhoneColumn;
    }

    public TableColumn<UserProperty, String> getStateColumn() {
        return stateColumn;
    }

    public void setStateColumn(TableColumn<UserProperty, String> stateColumn) {
        this.stateColumn = stateColumn;
    }

    public TableView<UserProperty> getApplicantTable() {
        return applicantTable;
    }

    public void setApplicantTable(TableView<UserProperty> applicantTable) {
        this.applicantTable = applicantTable;
    }
}