package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Lecturer;
import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Models.Student;
import com.agh.javaassignment.Views.LecturerCellFactory;
import com.agh.javaassignment.Views.StudentCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    public ListView<Student> students_listview;
    public ListView<Lecturer> lecturers_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        lecturers_listview.setItems(Model.getInstance().getLecturers());
        lecturers_listview.setCellFactory(e -> new LecturerCellFactory());
        students_listview.setItems(Model.getInstance().getStudents());
        students_listview.setCellFactory(e -> new StudentCellFactory());
    }

    private void initData() {
        if (Model.getInstance().getLecturers().isEmpty()) {
            Model.getInstance().setLecturers();
        }

        if (Model.getInstance().getStudents().isEmpty()) {
            Model.getInstance().setStudents();
        }
    }
}
