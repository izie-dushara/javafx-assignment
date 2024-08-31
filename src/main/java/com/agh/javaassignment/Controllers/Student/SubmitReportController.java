package com.agh.javaassignment.Controllers.Student;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class SubmitReportController implements Initializable {
    @FXML
    public Button submit_report_button;
    @FXML
    public Label error_lbl_l;
    @FXML
    public TextField assessmentId_fld;
    @FXML
    public TextField moodleLink_fld;
    @FXML
    public TextField assessmentIdUpdate_fld;
    @FXML
    public Button enter_id_btn;
    @FXML
    public TextField moodleLinkUpdate_fld;
    @FXML
    public Button update_report_button;
    @FXML
    public Label error_lbl_m;
    @FXML
    public Button delete_report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit_report_button.setOnAction(event -> createReport());
        enter_id_btn.setOnAction(event -> searchReport());
        update_report_button.setOnAction(event -> updateReport());
        delete_report_btn.setOnAction(event -> deleteReport());
    }


    private void createReport() {
        // Create Report
        int id = Model.getInstance().getLastEntryId("src/main/java/com/agh/javaassignment/Database/Report.txt");
        String assessmentId = assessmentId_fld.getText();
        String moodleLink = moodleLink_fld.getText();
        String studentId = Model.getInstance().getStudent().studentIdProperty().get();
        String studentName = Model.getInstance().getStudent().nameProperty().get();
        String submissionDate = LocalDate.now().toString();
        String feedback = "";
        String status = "Submitted";
        Model.getInstance().createReport(id, assessmentId, moodleLink, studentId, studentName, submissionDate, feedback, status);
        error_lbl_l.setText("Report Submit Successfully");
        emptyStudentFields();
        Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
    }
    private void searchReport() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Report.txt";
        String studentId = assessmentIdUpdate_fld.getText();
        Map<String, Object> reportData = Model.getInstance().readByReportId(filename, studentId);

        if (reportData != null) {
            if (Objects.equals((String) reportData.get("studentId"), Model.getInstance().getStudent().studentIdProperty().get())) {
                moodleLinkUpdate_fld.setText((String) reportData.get("moodleLink"));
            } else {
               error_lbl_m.setText("Unauthorized to View Report");
               Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_m);
            }

        } else {
            error_lbl_m.setText("ID Does Not Exist");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_m);
        }
    }

    private void updateReport() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Report.txt";
        Map<String, Object> reportData = Model.getInstance().readByReportId(filename, assessmentIdUpdate_fld.getText());
        String preUpdatedAssessmentId = assessmentIdUpdate_fld.getText();

        String moodleLink = moodleLinkUpdate_fld.getText();

        String assessmentId = (String) reportData.get("assessmentId");
        int id = (int) reportData.get("id");
        String status = (String) reportData.get("status");
        String studentId = (String) reportData.get("studentId");
        String studentName = (String) reportData.get("studentName");
        String submissionDate = (String) reportData.get("submissionDate");
        String feedback = (String) reportData.get("feedback");
        Map<String, Object> updatedReport = new HashMap<>();
        updatedReport.put("moodleLink", moodleLink);
        updatedReport.put("assessmentId", assessmentId);
        updatedReport.put("id", id);
        updatedReport.put("studentId", studentId);
        updatedReport.put("status", status);
        updatedReport.put("studentName", studentName);
        updatedReport.put("submissionDate", submissionDate);
        updatedReport.put("feedback", feedback);


        boolean success = Model.updateReport(filename, preUpdatedAssessmentId, updatedReport);

        if (success) {
            error_lbl_m.setText("Report Updated Successfully");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_m);
        } else {
            error_lbl_m.setText("Report Failed to be Created");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_m);
        }
        emptyStudentFields();

    }


    private void deleteReport() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Report.txt";
        Map<String, Object> reportData = Model.getInstance().readByReportId(filename, assessmentIdUpdate_fld.getText());
        String assessmentId = (String) reportData.get("assessmentId");
        searchReport();
        boolean success = Model.getInstance().deleteReport(filename, assessmentId);

        if (success) {
            error_lbl_m.setText("Student Deleted Successfully");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_m);
        } else {
            error_lbl_m.setText("Student Failed to be Deleted");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_m);
        }
        emptyStudentFields();
    }

    private void emptyStudentFields() {
        assessmentId_fld.setText("");
        moodleLink_fld.setText("");
        assessmentIdUpdate_fld.setText("");
        assessmentIdUpdate_fld.setText("");
    }
}
