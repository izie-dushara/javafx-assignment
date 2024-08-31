package com.agh.javaassignment.Controllers.PM;

import com.agh.javaassignment.Models.Model;
import com.agh.javaassignment.Views.PMMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PMController implements Initializable {
    public BorderPane pm_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getPMSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            if (Objects.requireNonNull(newVal) == PMMenuOptions.ADD_ASSESSMENT) {
                pm_parent.setCenter(Model.getInstance().getViewFactory().getAddAssessmentView());
            } else if (Objects.requireNonNull(newVal) == PMMenuOptions.REPORT){
                pm_parent.setCenter(Model.getInstance().getViewFactory().getReportView());
            }
        } ));

    }
}
