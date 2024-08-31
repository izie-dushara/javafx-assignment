package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Views.AdminMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    public Button add_people_btn;
    @FXML
    public Button manage_btn;
    @FXML
    public Button overview_btn;
    @FXML
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        add_people_btn.setOnAction(event -> onAdd());
        manage_btn.setOnAction(event -> onManage());
        overview_btn.setOnAction(event -> onOverview());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ADD);
    }

    private void onManage() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.MANAGE);
    }

    private void onOverview() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.OVERVIEW);
    }

    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}
