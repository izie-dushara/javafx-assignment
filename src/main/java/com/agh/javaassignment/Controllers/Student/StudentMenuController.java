package com.agh.javaassignment.Controllers.Student;

import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Views.AdminMenuOptions;
import com.agh.javaassignment.Views.StudentMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    @FXML
    public Button logout_btn;
    @FXML
    public Button report_btn;
    @FXML
    public Button presentation_btn;
    @FXML
    public Button result_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        report_btn.setOnAction(event -> onSubmitReport());
        presentation_btn.setOnAction(event -> onRequestPresentation());
        result_btn.setOnAction(event -> onCheckStatus());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onSubmitReport() {
        Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set(StudentMenuOptions.SUBMIT_REPORT);
    }

    private void onRequestPresentation() {
        Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set(StudentMenuOptions.REQUEST_PRESENTATION);
    }

    private void onCheckStatus() {
        Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set(StudentMenuOptions.CHECK_STATUS);
    }

    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}
