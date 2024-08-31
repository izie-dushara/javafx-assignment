package com.agh.javaassignment.Controllers.PM;

import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Views.AdminMenuOptions;
import com.agh.javaassignment.Views.PMMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PMMenuController implements Initializable {
    @FXML
    public Button add_people_btn;
    @FXML
    public Button manage_btn;
    @FXML
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        add_people_btn.setOnAction(event -> onAddAssessment());
        manage_btn.setOnAction(event -> onStudentReport());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onAddAssessment() {
        Model.getInstance().getViewFactory().getPMSelectedMenuItem().set(PMMenuOptions.ADD_ASSESSMENT);
    }

    private void onStudentReport() {
        Model.getInstance().getViewFactory().getPMSelectedMenuItem().set(PMMenuOptions.REPORT);
    }

    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}
