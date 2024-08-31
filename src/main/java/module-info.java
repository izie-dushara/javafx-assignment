module com.agh.javaassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires  de.jensd.fx.glyphs.fontawesome;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;


    opens com.agh.javaassignment to javafx.fxml;
    exports com.agh.javaassignment;
    exports com.agh.javaassignment.Controllers;
    exports com.agh.javaassignment.Controllers.Admin;
    exports com.agh.javaassignment.Controllers.Student;
    exports com.agh.javaassignment.Controllers.Lecturer;
    exports com.agh.javaassignment.Controllers.PM;
    exports com.agh.javaassignment.Models;
    exports com.agh.javaassignment.Views;

}