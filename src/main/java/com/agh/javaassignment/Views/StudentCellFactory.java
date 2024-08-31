package com.agh.javaassignment.Views;

import com.agh.javaassignment.Controllers.Admin.StudentCellController;
import com.agh.javaassignment.Models.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class StudentCellFactory extends ListCell<Student> {
    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);
        // Conditional if there's nothing to render
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // Get FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/StudentCell.fxml"));
            StudentCellController studentCellController = new StudentCellController(student);
            // Set controller
            loader.setController(studentCellController);
            setText(null);

            // Try load FXML file
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
