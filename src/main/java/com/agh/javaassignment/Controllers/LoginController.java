package com.agh.javaassignment.Controllers;

import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public PasswordField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public Label name_lbl;
    public TextField name_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // populate acc_selector
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.STUDENT, AccountType.LECTURER, AccountType.PM));
        // Default value of ADMIN
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        // Add listener to listen for change
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) login_btn.getScene().getWindow();

        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN) {
            // Evaluate Admin Login Credentials
            Model.getInstance().evaluateAdminCred(name_fld.getText(), password_fld.getText());
            if (Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
//                Close the stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                System.out.println("Here" + Model.getInstance().getAdminLoginSuccessFlag());
                name_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials");
                Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl);
            }
        } else if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.PM) {
            // Evaluate PM Login Credentials
            Model.getInstance().evaluatePMCred(name_fld.getText(), password_fld.getText());
            if (Model.getInstance().getPMLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showPMWindow();
//                Close the stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                name_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials");
                Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl);
            }
        } else if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.STUDENT) {
            // Evaluate Student Login Credentials
            Model.getInstance().evaluateStudentCred(name_fld.getText(), password_fld.getText());
            if (Model.getInstance().getStudentLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showStudentWindow();
//                Close the stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                name_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials");
                Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl);
            }
        } else if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.LECTURER) {
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }

    private void setAcc_selector() {
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        if (acc_selector.getValue() == AccountType.ADMIN) {
            name_lbl.setText("Admin Name:");
        } else if (acc_selector.getValue() == AccountType.PM) {
            name_lbl.setText("Project Manager ID:");
        } else if (acc_selector.getValue() == AccountType.STUDENT) {
            name_lbl.setText("Student ID:");
        } else if (acc_selector.getValue() == AccountType.LECTURER) {
            name_lbl.setText("Lecturer ID:");
        }
    }
}
