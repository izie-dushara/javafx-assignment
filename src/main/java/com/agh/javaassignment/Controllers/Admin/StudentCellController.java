package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCellController implements Initializable {
    @FXML
    public Label name_lbl;
    public Label intake_lbl;
    public Label sId_lbl;

    // Declare Model
    private final Student student;

    // Controller
    public StudentCellController(Student student){this.student = student;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name_lbl.textProperty().bind(student.nameProperty());
        intake_lbl.textProperty().bind(student.intakeProperty());
        sId_lbl.textProperty().bind(student.studentIdProperty());
    }
}
