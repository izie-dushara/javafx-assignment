package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Lecturer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerCellController implements Initializable {

    @FXML
    public Label name_lbl;
    @FXML
    public Label role;
    @FXML
    public Label lId_lbl;
    @FXML
    public Label department_lbl;

    // Declare Model
    private final Lecturer lecturer;

    // Make constructor
    public LecturerCellController(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name_lbl.textProperty().bind(lecturer.nameProperty());
        role.textProperty().bind(lecturer.roleProperty());
        lId_lbl.textProperty().bind(lecturer.lecturerIdProperty());
        department_lbl.textProperty().bind(lecturer.departmentProperty());
    }
}
