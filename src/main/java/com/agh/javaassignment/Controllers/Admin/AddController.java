package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    public TextField lName_fld;
    @FXML
    public TextField lPassword_fld;
    @FXML
    public TextField lId_fld;
    @FXML
    public CheckBox is_pm_box;
    @FXML
    public ChoiceBox<String> lDepartment;
    @FXML
    public Button create_lecturer_button;
    @FXML
    public TextField sName_fld;
    @FXML
    public TextField sPassword_fld;
    @FXML
    public TextField sId_fld;
    @FXML
    public ChoiceBox<String> month;
    @FXML
    public ChoiceBox<String> year;
    @FXML
    public Button create_student_btn;
    @FXML
    public Label error_lbl_l;
    @FXML
    public Label error_lbl_s;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateStudentChoiceBox();
        populateLectureChoiceBox();
        create_student_btn.setOnAction(event -> createStudent());
        create_lecturer_button.setOnAction(event -> createLecturer());
    }

    private void createStudent() {
        // Create Student
        int id = Model.getInstance().getLastEntryId("src/main/java/com/agh/javaassignment/Database/Student.txt") + 1;
        String name = sName_fld.getText();
        String password = sPassword_fld.getText();
        String studentId = sId_fld.getText();
        String intake = month.getValue() +" "+year.getValue();
        Model.getInstance().createStudent(id, name, password, intake, studentId);
        error_lbl_s.setText("Student Created Successfully");
        emptyStudentFields();
        Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_s);
    }

    private void createLecturer() {
        // Create Lecturer
        boolean isPM = false;
        String role = "Not Assigned";
        int id = Model.getInstance().getLastEntryId("src/main/java/com/agh/javaassignment/Database/Lecturer.txt") + 1;
        String name = lName_fld.getText();
        String password = lPassword_fld.getText();
        String lecturerId = lId_fld.getText();
        if (is_pm_box.isSelected()) {
            isPM = true;
            role = "Project Manager";
        }
        String department = lDepartment.getValue();
        Model.getInstance().createLecturer(id, name, password, lecturerId, role, department, isPM);
        error_lbl_l.setText("Lecturer Created Successfully");
        emptyLecturerFields();
        Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
    }


    private void populateStudentChoiceBox() {
        month.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "December");
        month.setValue("January");
        year.getItems().addAll("2021", "2022", "2023", "2024");
        year.setValue("2024");
    }


    private void populateLectureChoiceBox() {
        lDepartment.getItems().addAll("Arts","Computer Science", "Mathematics", "Cyber Security");
        lDepartment.setValue("Arts");
    }

    private void emptyStudentFields() {
        sName_fld.setText("");
        sId_fld.setText("");
        sPassword_fld.setText("");
        month.setValue("January");
        year.setValue("2024");
    }

    private void emptyLecturerFields() {
        lName_fld.setText("");
        lId_fld.setText("");
        lPassword_fld.setText("");
        month.setValue("January");
        year.setValue("2024");
        lDepartment.setValue("Arts");
        is_pm_box.setSelected(false);
    }
}
