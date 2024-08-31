package com.agh.javaassignment.Controllers.Admin;

import com.agh.javaassignment.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case MANAGE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getUpdateView());
                case OVERVIEW ->  admin_parent.setCenter(Model.getInstance().getViewFactory().getOverviewView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAddView());
            }
        } ));

    }
}
