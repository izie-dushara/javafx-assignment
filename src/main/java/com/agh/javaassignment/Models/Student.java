package com.agh.javaassignment.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty password;
    private final StringProperty intake;
    private final StringProperty studentId;

    public Student(int id, String name, String password, String intake, String studentId) {
        this.id = new SimpleIntegerProperty(this, "Id", id);
        this.name = new SimpleStringProperty(this, "Name", name);
        this.password = new SimpleStringProperty(this, "Password", password);
        this.intake = new SimpleStringProperty(this, "intake", intake);
        this.studentId = new SimpleStringProperty(this, "StudentId", studentId);
    }

    public IntegerProperty idProperty() {
        return this.id;
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty passwordProperty() {
        return this.password;
    }

    public StringProperty intakeProperty() {
        return this.intake;
    }

    public StringProperty studentIdProperty() {
        return this.studentId;
    }
}

