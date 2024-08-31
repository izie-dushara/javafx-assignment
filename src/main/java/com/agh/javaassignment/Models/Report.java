package com.agh.javaassignment.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Report {
    private final IntegerProperty id;
    private final StringProperty assessmentId;
    private final StringProperty moodleLink;
    private final StringProperty status;
    private final StringProperty studentId;
    private final StringProperty studentName;
    private final ObjectProperty<LocalDate> submissionDate;
    private final StringProperty feedback;

    public Report(int id, String assessmentId, String moodleLink, String status, String studentId, String studentName, LocalDate submissionDate, String feedback) {
        this.id = new SimpleIntegerProperty(this, "Id", id);
        this.assessmentId = new SimpleStringProperty(this, "AssessmentId", assessmentId);
        this.moodleLink = new SimpleStringProperty(this, "MoodleLink", moodleLink);
        this.status = new SimpleStringProperty(this, "Status", status);
        this.studentId = new SimpleStringProperty(this, "StudentId", studentId);
        this.submissionDate = new SimpleObjectProperty<LocalDate>(this, "SubmissionDate", submissionDate);
        this.studentName = new SimpleStringProperty(this, "StudentName", studentName);
        this.feedback = new SimpleStringProperty(this, "Feedback", feedback);
    }

    public IntegerProperty idProperty() {
        return this.id;
    }

    public StringProperty assessmentIdProperty() {
        return this.assessmentId;
    }

    public StringProperty moodleLinkProperty() {
        return this.moodleLink;
    }

    public StringProperty statusProperty() {
        return this.status;
    }

    public StringProperty studentId() {
        return this.studentId;
    }

    public StringProperty studentNameProperty() {
        return this.studentName;
    }

    public ObjectProperty<LocalDate> submissionDateProperty() {
        return this.submissionDate;
    }

    public StringProperty feedbackProperty() {
        return this.feedbackProperty();
    }


}
