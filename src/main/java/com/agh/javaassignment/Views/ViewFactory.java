package com.agh.javaassignment.Views;

import com.agh.javaassignment.Controllers.Admin.AdminController;
import com.agh.javaassignment.Controllers.PM.PMController;
import com.agh.javaassignment.Controllers.Student.StudentController;
import javafx.animation.PauseTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewFactory {
    // AccountType
    private AccountType loginAccountType;
    /*
    * Admin Views
    * */
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private final ObjectProperty<PMMenuOptions> pmSelectedMenuItem;
    private final ObjectProperty<StudentMenuOptions> studentSelectedMenuItem;
    private AnchorPane addView;
    private  AnchorPane updateView;
    private AnchorPane overviewView;
    private AnchorPane addAssessmentView;
    private AnchorPane reportView;
    private AnchorPane submitReportView;
    private AnchorPane requestPresentationView;
    private AnchorPane checkStatusView;


    // Constructor
    public ViewFactory() {
        this.loginAccountType = AccountType.ADMIN;
        // Admin
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        // PM
        this.pmSelectedMenuItem = new SimpleObjectProperty<>();
        // Student
        this.studentSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public ObjectProperty<PMMenuOptions> getPMSelectedMenuItem() {
        return pmSelectedMenuItem;
    }


    public ObjectProperty<StudentMenuOptions> getStudentSelectedMenuItem() {
        return studentSelectedMenuItem;
    }

    public void delayLabelSeconds(Label informationLabel) {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> informationLabel.setText(""));
        pause.play();
    }

    public AnchorPane getAddView() {
        // If dashboardView is not already created. Create it
        // prevent extensive resource usage
        if (addView == null) {
            try {
                addView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Add.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return addView;
    }

    public AnchorPane getUpdateView() {
        if (updateView == null) {
            try {
                updateView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Update.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updateView;
    }

    public AnchorPane getOverviewView() {
        if (overviewView == null) {
            try {
                overviewView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Overview.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return overviewView;
    }

    /*
    * PM View
    * */


    public AnchorPane getAddAssessmentView() {
        if (addAssessmentView == null) {
            try {
                addAssessmentView = new FXMLLoader(getClass().getResource("/Fxml/PM/AddAssessment.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return addAssessmentView;
    }

    public AnchorPane getReportView() {
        if (reportView == null) {
            try {
                reportView = new FXMLLoader(getClass().getResource("/Fxml/PM/Report.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reportView;
    }

    /*
    * Student View
     * */

    public  AnchorPane getSubmitReportView() {
        if (submitReportView == null) {
            try {
                submitReportView = new FXMLLoader(getClass().getResource("/Fxml/Student/SubmitReport.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return submitReportView;
    }

    public AnchorPane getRequestPresentationView() {
        if (requestPresentationView == null) {
            try {
                requestPresentationView = new FXMLLoader(getClass().getResource("/Fxml/Student/RequestPresentation.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return requestPresentationView;
    }

    public AnchorPane getCheckStatusView() {
        if (checkStatusView == null) {
            try {
                checkStatusView = new FXMLLoader(getClass().getResource("/Fxml/Student/CheckStatus.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return checkStatusView;
    }

    // Show login page
    public void showLoginWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(fxmlLoader);

    }

    // Show the full Admin window
    public void showAdminWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        // Set controller for the FXML page
        fxmlLoader.setController(adminController);
        createStage(fxmlLoader);
    }

    // Show the full PM window
    public void showPMWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/PM/PM.fxml"));
        PMController pmController = new PMController();
        // Set controller for the FXML page
        fxmlLoader.setController(pmController);
        createStage(fxmlLoader);
    }

    // Show the full Student window
    public void showStudentWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Student/Student.fxml"));
        StudentController studentController = new StudentController();
        fxmlLoader.setController(studentController);
        createStage(fxmlLoader);
    }

    // Creating stage when transitioning everytime
    private void createStage(FXMLLoader fxmlLoader) {

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("AGH");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
