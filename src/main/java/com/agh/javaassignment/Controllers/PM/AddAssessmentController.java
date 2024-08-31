package com.agh.javaassignment.Controllers.PM;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AddAssessmentController implements Initializable {
    public TextField student_fld;
    public Button student_btn;
    public TextField sName_fld;
    public ChoiceBox<String> sAssessment_box;
    public TextField supervisor_fld;
    public Button supervisor_btn;
    public TextField supervisorName_fld;
    public Label error_lbl_l;
    public TextField sMarker_fld;
    public Button sMarker_btn;
    public TextField sMarker_name;
    public Button add_assessment_btn;
    public Label student_err_label;
    public Label supervisor_error_lbl;
    public Label sMarker_error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateStudentChoiceBox();
        student_btn.setOnAction(event -> searchStudent());
        supervisor_btn.setOnAction(event -> searchLecturer(supervisor_fld, supervisorName_fld, supervisor_error_lbl));
        sMarker_btn.setOnAction(event -> searchLecturer(sMarker_fld, sMarker_name, sMarker_error_lbl));
        add_assessment_btn.setOnAction(event -> createAssessment());
    }

    private void populateStudentChoiceBox() {
        sAssessment_box.getItems().addAll("Internship", "Investigation Reports", "CP1", "CP2", "RMCP", "FYP");
        sAssessment_box.setValue("Investigation Reports");
    }


    private void searchLecturer(TextField roleFld, TextField nameFLd, Label errorLbl) {
        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        String lecturerId = roleFld.getText();
        Map<String, Object> lecturerData = Model.getInstance().readByLecturerId(filename, lecturerId);

        if (lecturerData != null) {
            if (!((boolean) lecturerData.get("isPM"))) {
                nameFLd.setText((String) lecturerData.get("name"));
            } else {
                errorLbl.setText("Lecturer is a Project Manager");
                Model.getInstance().getViewFactory().delayLabelSeconds(errorLbl);
            }
        } else {
            errorLbl.setText("ID Does Not Exist");
            Model.getInstance().getViewFactory().delayLabelSeconds(errorLbl);
        }
    }


    private void searchStudent() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        String studentId = student_fld.getText();
        Map<String, Object> studentData = Model.getInstance().readByStudentId(filename, studentId);

        if (studentData != null) {

            sName_fld.setText((String) studentData.get("name"));
        } else {
            student_err_label.setText("ID Does Not Exist");
            Model.getInstance().getViewFactory().delayLabelSeconds(student_err_label);
        }
    }

    private void createAssessment() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Assessment.txt";
        int id = Model.getInstance().getLastEntryId(filename) + 1;
        String assessmentId = student_fld.getText() + Model.getInstance().getLecturer().lecturerIdProperty().get();
        String studentId = student_fld.getText();
        String supervisorId = supervisor_fld.getText();
        String secondMarkerId = sMarker_fld.getText();
        String type = sAssessment_box.getValue();
        String projectManagerId = Model.getInstance().getLecturer().lecturerIdProperty().get();
        Model.getInstance().createAssessment(id, assessmentId, studentId, supervisorId, secondMarkerId, type, projectManagerId);
        error_lbl_l.setText("Assessment Created Successfully");
        Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        emptyFields();
    }
     private void emptyFields() {
        student_fld.setText("");
        sName_fld.setText("");
        sAssessment_box.setValue("Investigation Reports");
        supervisor_fld.setText("");
        supervisorName_fld.setText("");
        sMarker_fld.setText("");
        sMarker_name.setText("");
     }
}
