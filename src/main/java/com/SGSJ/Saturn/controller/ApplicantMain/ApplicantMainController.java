package com.SGSJ.Saturn.controller.ApplicantMain;

import com.SGSJ.Saturn.SaturnSystemApplication;
import com.SGSJ.Saturn.config.DataHolder;
import com.SGSJ.Saturn.controller.SidePanelController;
import com.SGSJ.Saturn.domain.User.UserService;
import com.SGSJ.Saturn.view.SaturnView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class ApplicantMainController extends SidePanelController {
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
    private UserService userService;

    @Override
    synchronized public void initialize(URL location, ResourceBundle resources) {
        userData.clear();
        userService.getAll().stream().forEach(user -> {
            userData.add(new UserProperty(user));
        });

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        mainPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().mainPhoneProperty());
        secondaryPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().secondaryPhoneProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());

        applicantTable.setItems(userData);
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
}