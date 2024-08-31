package com.agh.javaassignment.Views;

import com.agh.javaassignment.Controllers.PM.ReportCellController;
import com.agh.javaassignment.Models.Report;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ReportCellFactory extends ListCell<Report> {
    @Override
    protected void updateItem(Report report, boolean empty) {
        super.updateItem(report, empty);

        // Conditional if there's nothing to render
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // Get FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/PM/ReportCell.fxml"));
            ReportCellController reportCellController = new ReportCellController(report);
            // Set Controller
            loader.setController(reportCellController);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
