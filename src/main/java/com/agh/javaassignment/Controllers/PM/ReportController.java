package com.agh.javaassignment.Controllers.PM;

import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Models.Report;
import com.agh.javaassignment.Views.ReportCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    public ListView<Report> reports_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        reports_listview.setItems(Model.getInstance().getReports());
        reports_listview.setCellFactory(e -> new ReportCellFactory());
    }

    private void initData() {
      if (Model.getInstance().getReports().isEmpty()) {
          Model.getInstance().setReports();
      }
    }
}
