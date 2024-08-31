package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
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
    public TextField sName_fld;
    @FXML
    public TextField sPassword_fld;
    @FXML
    public TextField sId_fld;
    @FXML
    public ChoiceBox<String> season;
    @FXML
    public ChoiceBox<String> year;
    @FXML
    public Label error_lbl_l;
    @FXML
    public Label error_lbl_s;
    @FXML
    public TextField id_lecturer_fld;
    @FXML
    public Button search_lecturer_btn;
    @FXML
    public Button update_lecturer_button;
    @FXML
    public Button delete_lecturer_btn;
    @FXML
    public TextField id_student_fld;
    @FXML
    public Button search_student_btn;
    @FXML
    public Button update_student_btn;
    @FXML
    public Button delete_student_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateStudentChoiceBox();
        populateLectureChoiceBox();
        search_student_btn.setOnAction(event -> searchStudent());
        update_student_btn.setOnAction(event -> updateStudent());
        delete_student_btn.setOnAction(event -> deleteStudent());
        search_lecturer_btn.setOnAction(event -> searchLecturer());
        delete_lecturer_btn.setOnAction(event -> deleteLecturer());
        update_lecturer_button.setOnAction(event -> updateLecturer());
    }

    private void searchStudent() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        String studentId = id_student_fld.getText();
        Map<String, Object> studentData = Model.getInstance().readByStudentId(filename, studentId);

        if (studentData != null) {

            sName_fld.setText((String) studentData.get("name"));
            sPassword_fld.setText((String) studentData.get("password"));
            sId_fld.setText((String) studentData.get("studentId"));
            String intake = (String) studentData.get("intake");
            String[] parts = intake.split(" ");
            if (parts.length == 2) {
                season.setValue(parts[0]);
                year.setValue(parts[1]);

            }
        } else {
            error_lbl_s.setText("ID Does Not Exist");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_s);
        }
    }

    private void updateStudent() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        Map<String, Object> studentData = Model.getInstance().readByStudentId(filename, id_student_fld.getText());
        String preUpdatedStudentId = id_student_fld.getText();

        int id = (int) studentData.get("id");
        String name = sName_fld.getText();
        String password = sPassword_fld.getText();
        String studentId = sId_fld.getText();
        String intake = season.getValue() + " " + year.getValue();


        Map<String, Object> updatedStudent = new HashMap<>();
        updatedStudent.put("id", id);
        updatedStudent.put("name", name);
        updatedStudent.put("password", password);
        updatedStudent.put("studentId", studentId);
        updatedStudent.put("intake", intake);

        boolean success = Model.updateStudent(filename, preUpdatedStudentId, updatedStudent);

        if (success) {
            error_lbl_s.setText("Student Updated Successfully");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_s);
        } else {
            error_lbl_s.setText("Student Failed to be Created");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_s);
        }
        emptyStudentFields();

    }

    private void deleteStudent() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        Map<String, Object> studentData = Model.getInstance().readByStudentId(filename, id_student_fld.getText());
        String studentId = (String) studentData.get("studentId");
        searchStudent();
        boolean success = Model.getInstance().deleteStudent(filename, studentId);

        if (success) {
            error_lbl_s.setText("Student Deleted Successfully");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_s);
        } else {
            error_lbl_s.setText("Student Failed to be Deleted");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_s);
        }
        emptyStudentFields();
    }

    private void searchLecturer() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        String lecturerId = id_lecturer_fld.getText();
        Map<String, Object> lecturerData = Model.getInstance().readByLecturerId(filename, lecturerId);

        if (lecturerData != null) {

            lName_fld.setText((String) lecturerData.get("name"));
            lPassword_fld.setText((String) lecturerData.get("password"));
            lId_fld.setText((String) lecturerData.get("lecturerId"));
            is_pm_box.setSelected((boolean) lecturerData.get("isPM"));
            lDepartment.setValue((String) lecturerData.get("department"));
        } else {
            error_lbl_l.setText("ID Does Not Exist");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        }
    }

    private void updateLecturer() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        Map<String, Object> lecturerData = Model.getInstance().readByLecturerId(filename, id_lecturer_fld.getText());
        String preUpdatedLecturerId = id_lecturer_fld.getText();

        int id = (int) lecturerData.get("id");
        String name = lName_fld.getText();
        String password = lPassword_fld.getText();
        String lecturerId = lId_fld.getText();
        boolean isPM = false;
        String role = "";
        if (is_pm_box.isSelected()) {
            isPM = true;
            role = "Project Manager";
        }
        String department = lDepartment.getValue();

        Map<String, Object> updatedLecturer = new HashMap<>();
        updatedLecturer.put("id", id);
        updatedLecturer.put("name", name);
        updatedLecturer.put("password", password);
        updatedLecturer.put("lecturerId", lecturerId);
        updatedLecturer.put("role", role);
        updatedLecturer.put("department", department);
        updatedLecturer.put("isPM", isPM);

        boolean success = Model.updateLecturer(filename, preUpdatedLecturerId, updatedLecturer);

        if (success) {
            error_lbl_l.setText("Lecturer Updated Successfully");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        } else {
            error_lbl_l.setText("Lecturer Failed to be Created");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        }

        emptyLecturerFields();

    }

    private void deleteLecturer() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        Map<String, Object> lecturerData = Model.getInstance().readByLecturerId(filename, id_lecturer_fld.getText());
        String lecturerId = (String) lecturerData.get("lecturerId");
        searchLecturer();
        boolean success = Model.getInstance().deleteLecturer(filename, lecturerId);

        if (success) {
            error_lbl_l.setText("Lecturer Deleted Successfully");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        } else {
            error_lbl_l.setText("Student Failed to be Deleted");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl_l);
        }

        emptyLecturerFields();
    }

    private void populateStudentChoiceBox() {
        season.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "December");
        year.getItems().addAll("2021", "2022", "2023", "2024");
    }


    private void populateLectureChoiceBox() {
        lDepartment.getItems().addAll("Arts", "Computer Science", "Mathematics", "Cyber Security");
    }

    private void emptyStudentFields() {
        sName_fld.setText("");
        sId_fld.setText("");
        sPassword_fld.setText("");
        id_student_fld.setText("");
        season.setValue("January");
        year.setValue("2024");
    }

    private void emptyLecturerFields() {
        lName_fld.setText("");
        lId_fld.setText("");
        lPassword_fld.setText("");
        id_lecturer_fld.setText("");
        season.setValue("January");
        year.setValue("2024");
        lDepartment.setValue("Arts");
        is_pm_box.setSelected(false);
    }

}
