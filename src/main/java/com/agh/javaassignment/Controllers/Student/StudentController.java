package com.agh.javaassignment.Controllers.Student;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    public BorderPane student_parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getStudentSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case REQUEST_PRESENTATION -> student_parent.setCenter(Model.getInstance().getViewFactory().getRequestPresentationView());
                case CHECK_STATUS ->  student_parent.setCenter(Model.getInstance().getViewFactory().getCheckStatusView());
                default -> student_parent.setCenter(Model.getInstance().getViewFactory().getSubmitReportView());
            }
        } ));

    }
}
