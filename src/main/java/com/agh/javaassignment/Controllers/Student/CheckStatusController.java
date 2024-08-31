package com.agh.javaassignment.Controllers.Student;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class CheckStatusController implements Initializable {
    public TextField assessmentId_fld;
    public Button getStatus_btn;
    public Label submissionStat_lbl;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getStatus_btn.setOnAction(event -> searchReport());
    }

    private void searchReport() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Report.txt";
        String studentId = assessmentId_fld.getText();
        Map<String, Object> reportData = Model.getInstance().readByReportId(filename, studentId);

        if (reportData != null) {
            if (Objects.equals((String) reportData.get("studentId"), Model.getInstance().getStudent().studentIdProperty().get())) {
                submissionStat_lbl.setText((String) reportData.get("status"));
            } else {
                error_lbl.setText("Unauthorized to View Report");
                Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl);
            }

        } else {
            error_lbl.setText("ID Does Not Exist");
            Model.getInstance().getViewFactory().delayLabelSeconds(error_lbl);
        }

        emptyFields();
    }

    private void emptyFields() {
        assessmentId_fld.setText("");
    }
}
