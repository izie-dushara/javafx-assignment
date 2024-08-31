package com.agh.javaassignment.Controllers.PM;

import com.agh.javaassignment.Models.Report;
import com.agh.javaassignment.Models.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportCellController implements Initializable {
    private final Report report;
    @FXML
    public Label assessment_id_lbl;
    public Label student_id_lbl;
    public Label submitDate_lbl;
    public Label status_lbl;

    // Controller
    public ReportCellController(Report report){this.report = report;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        assessment_id_lbl.textProperty().bind(report.assessmentIdProperty());
        student_id_lbl.textProperty().bind(report.studentNameProperty());
        submitDate_lbl.textProperty().bind(report.submissionDateProperty().asString());
        status_lbl.textProperty().bind(report.statusProperty());
    }
}
