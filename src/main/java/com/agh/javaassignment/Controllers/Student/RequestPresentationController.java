package com.agh.javaassignment.Controllers.Student;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class RequestPresentationController implements Initializable {
    public TextField assessmentId_lbl;
    public DatePicker datepicker;
    public Button request_presentation_btn;
    public Label error_lbl_l;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        request_presentation_btn.setOnAction(event -> createPresentation());
    }

    private Map<String, Object> readAssessment() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Assessment.txt";
        return Model.getInstance().readByReportId(filename, assessmentId_lbl.getText());
    }

    private void createPresentation() {
        Map<String, Object> data = readAssessment();
        String filename = "src/main/java/com/agh/javaassignment/Database/Presentation.txt";
        int id = Model.getInstance().getLastEntryId(filename) + 1;
        String assessmentId = (String) data.get("assessmentId");
        String confirmedDate = "";
        String requestedDate = datepicker.getValue().toString();
        String supervisorId = (String) data.get("supervisorId");
        String slot = "";
        String requestedStatus = "Submitted";
        String studentId = Model.getInstance().getStudent().studentIdProperty().get();
        System.out.println(id + assessmentId + confirmedDate + requestedDate + supervisorId + slot + requestedStatus + studentId);
        Model.getInstance().createPresentation(id, assessmentId, confirmedDate, requestedDate, supervisorId, slot, requestedStatus, studentId);
        error_lbl_l.setText("Presentation Request Send. \nPlease communicate with your lecturer to approve and get time slot");
        Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        emptyFields();
    }

    private void emptyFields() {
        datepicker.setValue(null);
        assessmentId_lbl.setText("");
    }
}
