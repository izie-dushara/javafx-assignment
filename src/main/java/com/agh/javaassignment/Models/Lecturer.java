package com.agh.javaassignment.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lecturer {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty password;
    private final StringProperty lecturerId;
    private final StringProperty department;
    private final StringProperty role;
    private final BooleanProperty isPM;

    public Lecturer(int id, String name, String password, String lecturerId, String department, String role, boolean isPm) {
        this.id = new SimpleIntegerProperty(this, "Id", id);
        this.name = new SimpleStringProperty(this, "Name", name);
        this.password = new SimpleStringProperty(this, "Password", password);
        this.lecturerId = new SimpleStringProperty(this, "LecturerId", lecturerId);
        this.department = new SimpleStringProperty(this, "Department", department);
        this.role = new SimpleStringProperty(this, "Role", role);
        this.isPM = new SimpleBooleanProperty(this, "IsPM", isPm);
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

    public StringProperty lecturerIdProperty() {
        return this.lecturerId;
    }

    public StringProperty departmentProperty() {
        return this.department;
    }

    public StringProperty roleProperty() {
        return this.role;
    }

    public BooleanProperty isPMProperty() {
        return this.isPM;
    }
}
