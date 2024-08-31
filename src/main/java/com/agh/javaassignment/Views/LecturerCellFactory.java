package com.agh.javaassignment.Views;

import com.agh.javaassignment.Controllers.Admin.LecturerCellController;
import com.agh.javaassignment.Models.Lecturer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class LecturerCellFactory extends ListCell<Lecturer> {
    @Override
    protected void updateItem(Lecturer lecturer, boolean empty) {
        super.updateItem(lecturer, empty);

        // Conditional if there's nothing to render
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // Get FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/LecturerCell.fxml"));
            LecturerCellController lecturerCellController = new LecturerCellController(lecturer);
            // Set Controller
            loader.setController(lecturerCellController);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
