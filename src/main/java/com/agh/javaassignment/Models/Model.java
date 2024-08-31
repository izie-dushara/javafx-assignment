package com.agh.javaassignment.Models;
import com.agh.javaassignment.Views.ViewFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {
    private final ViewFactory viewFactory = new ViewFactory();
    private static Model model;
    private static ObjectMapper objectMapper;
    private final Lecturer lecturer;
    private final Student student;
    private final ObservableList<Lecturer> lecturers;
    private final ObservableList<Student> students;
    private final ObservableList<Report> reports;
    private boolean adminLoginSuccessFlag;
    private boolean pmLoginSuccessFlag;
    private boolean studentLoginSuccessFlag;

    private Model() {
        this.lecturers = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
        this.reports = FXCollections.observableArrayList();
        this.adminLoginSuccessFlag = false;
        this.pmLoginSuccessFlag = false;
        this.studentLoginSuccessFlag = false;
        this.lecturer = new Lecturer(0, "","","","","",false);
        this.student = new Student(0,"", "", "","");
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return this.viewFactory;
    }

    public static List<Map<String, Object>> readJsonArrayFromFile(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void writeJsonArrayToFile(String filename, List<Map<String, Object>> data) {
        try {
            objectMapper.writeValue(new File(filename), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createEntry(String filename, Map<String, Object> newEntry) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        // Debugging: Print JSON String to console
        System.out.println("Generated JSON: " + newEntry);
        data.add(newEntry);
        writeJsonArrayToFile(filename, data);
    }


    public void createStudent(int id, String name, String password, String intake, String studentId) {
        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        createEntry(filename, Map.of("id", id, "name", name, "password", password, "intake", intake, "studentId", studentId));
    }

    public void createLecturer(int id, String name, String password, String lecturerId, String role, String department, boolean isPM) {
        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        createEntry(filename, Map.of("id", id, "name", name, "password", password, "lecturerId", lecturerId, "role", role, "department", department, "isPM", isPM));
    }

    public void createReport(int id, String assessmentId, String moodleLink,String studentId, String studentName, String submissionDate, String feedback, String status) {
        String filename = "src/main/java/com/agh/javaassignment/Database/Report.txt";
        createEntry(filename, Map.of("id", id, "assessmentId",assessmentId, "moodleLink", moodleLink, "studentId", studentId, "studentName", studentName, "submissionDate", submissionDate, "feedback", feedback, "status", status));
    }


    public void createAssessment(int id, String assessmentId, String studentId, String supervisorId, String secondMarkerId, String type, String projectManagerId) {
        String filename = "src/main/java/com/agh/javaassignment/Database/Assessment.txt";
        createEntry(filename,
        Map.of("id", id, "assessmentId", assessmentId, "studentId", studentId, "supervisorId", supervisorId, "secondMarkerId", secondMarkerId, "type", type, "projectManagerId", projectManagerId));
    }

    public void createPresentation(int id, String assessmentId, String confirmedDate, String requestedDate, String supervisorId, String slot, String requestedStatus, String studentId) {
        String filename = "src/main/java/com/agh/javaassignment/Database/Presentation.txt";
        createEntry(filename,
                Map.of("id", id, "assessmentId", assessmentId, "studentId", studentId, "supervisorId", supervisorId, "confirmedDate", confirmedDate, "slot", slot, "requestedDate", requestedDate, "requestedStatus", requestedStatus));
    }

    public ObservableList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        File file = new File(filename);
        List<Map<String, Object>> lecturerData;
        try {
            lecturerData = objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> clientMap : lecturerData) {
                int id = (int) clientMap.get("id");
                String name = (String) clientMap.get("name");
                String password = (String) clientMap.get("password");
                String lecturerId = (String) clientMap.get("lecturerId");
                boolean isPM = (boolean) clientMap.get("isPM");
                String department = (String) clientMap.get("department");
                String role = (String) clientMap.get("role");
                lecturers.add(new Lecturer(id, name, password, lecturerId, department, role, isPM));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        File file = new File(filename);
        List<Map<String, Object>> studentData;
        try {
            studentData = objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> clientMap : studentData) {
                int id = (int) clientMap.get("id");
                String name = (String) clientMap.get("name");
                String password = (String) clientMap.get("password");
                String studentId = (String) clientMap.get("studentId");
                String intake = (String) clientMap.get("intake");
                students.add(new Student(id, name, password, intake, studentId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Report> getReports() {
        return reports;
    }

    public void setReports() {
        String filename = "src/main/java/com/agh/javaassignment/Database/Report.txt";
        File file = new File(filename);
        List<Map<String, Object>> reportsData;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            reportsData = objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> reportMap : reportsData) {
                int id = (int) reportMap.get("id");
                String assessmentId = (String) reportMap.get("assessmentId");
                String moodleLink = (String) reportMap.get("moodleLink");
                String status = (String) reportMap.get("status");
                String studentId = (String) reportMap.get("studentId");
                String studentName = (String) reportMap.get("studentName");
                String submissionDateString =  (String) reportMap.get("submissionDate");
                LocalDate submissionDate = LocalDate.parse(submissionDateString, formatter);
                String feedback = (String) reportMap.get("feedback");
                reports.add(new Report(id, assessmentId, moodleLink, status, studentId, studentName, submissionDate, feedback));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> readByStudentId(String filename, String id) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (Map<String, Object> entry : data) {
            if (entry.get("studentId").equals(id)) {
                return entry;
            }
        }
        return null;
    }


    public Map<String, Object> readByLecturerId(String filename, String id) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (Map<String, Object> entry : data) {
            if (entry.get("lecturerId").equals(id)) {
                return entry;
            }
        }
        return null;
    }

    public Map<String, Object> readByReportId(String filename, String id) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (Map<String, Object> entry: data) {
            if (entry.get("assessmentId").equals(id)) {
                System.out.println(entry);
                return entry;
            }
        }

        return null;
    }




    public static boolean updateStudent(String filename, String id, Map<String, Object> updatedEntry) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("studentId").equals(id)) {
                data.set(i, updatedEntry);
                writeJsonArrayToFile(filename, data);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String filename, String id) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("studentId").equals(id)) {
                data.remove(i);
                writeJsonArrayToFile(filename, data);
                return true;
            }
        }
        return false;
    }


    public static boolean updateLecturer(String filename, String id, Map<String, Object> updatedEntry) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("lecturerId").equals(id)) {
                data.set(i, updatedEntry);
                writeJsonArrayToFile(filename, data);
                return true;
            }
        }
        return false;
    }

    public boolean deleteLecturer(String filename, String id) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("lecturerId").equals(id)) {
                data.remove(i);
                writeJsonArrayToFile(filename, data);
                return true;
            }
        }
        return false;
    }

    public static boolean updateReport(String filename, String id, Map<String, Object> updatedEntry) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("assessmentId").equals(id)) {
                data.set(i, updatedEntry);
                writeJsonArrayToFile(filename, data);
                return true;
            }
        }
        return false;
    }

    public boolean deleteReport(String filename, String id) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("assessmentId").equals(id)) {
                data.remove(i);
                writeJsonArrayToFile(filename, data);
                return true;
            }
        }
        return false;
    }
    /*
    * Login
    * */

    // Admin
    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }


    // Login
    // Method to evaluate admin credentials
    public void evaluateAdminCred(String adminName, String password) {

        String filename = "src/main/java/com/agh/javaassignment/Database/Admin.txt";
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (Map<String, Object> entry : data) {
            if (entry.get("adminName").equals(adminName) && entry.get("password").equals(password)) {
                this.setAdminLoginSuccessFlag(true);
            }
        }
    }

    public Lecturer getLecturer() {
        return lecturer;
    }
    // PM
    public boolean getPMLoginSuccessFlag() {
        return this.pmLoginSuccessFlag;
    }

    public void setPMLoginSuccessFlag(boolean pmLoginSuccessFlag) {
        this.pmLoginSuccessFlag = pmLoginSuccessFlag;
    }

    // Method to evaluate project manager credentials
    public void evaluatePMCred(String pMId, String password) {

        String filename = "src/main/java/com/agh/javaassignment/Database/Lecturer.txt";
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (Map<String, Object> entry : data) {
            if (entry.get("lecturerId").equals(pMId) && entry.get("password").equals(password) && entry.get("isPM").equals(true)) {
                this.lecturer.idProperty().set((int) entry.get("id"));
                this.lecturer.lecturerIdProperty().set((String) entry.get("lecturerId"));
                this.lecturer.nameProperty().set((String) entry.get("name"));
                this.lecturer.departmentProperty().set((String) entry.get("department"));
                this.lecturer.passwordProperty().set((String) entry.get("password"));
                this.lecturer.roleProperty().set((String) entry.get("role"));
                this.lecturer.isPMProperty().set((boolean) entry.get("isPM"));
                this.setPMLoginSuccessFlag(true);
            }
        }
    }

    // Student
    public boolean getStudentLoginSuccessFlag() {
        return this.studentLoginSuccessFlag;
    }

    public void setStudentLoginSuccessFlag(boolean studentLoginSuccessFlag) {
        this.studentLoginSuccessFlag = studentLoginSuccessFlag;
    }


    // Method to evaluate student credentials
    public void evaluateStudentCred(String studentId, String password) {

        String filename = "src/main/java/com/agh/javaassignment/Database/Student.txt";
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        for (Map<String, Object> entry : data) {
            if (entry.get("studentId").equals(studentId) && entry.get("password").equals(password)) {
                System.out.println(studentId + " " +password);
                this.student.idProperty().set((int) entry.get("id"));
                this.student.studentIdProperty().set((String) entry.get("studentId"));
                this.student.nameProperty().set((String) entry.get("name"));
                this.student.intakeProperty().set((String) entry.get("intake"));
                this.student.passwordProperty().set((String) entry.get("password"));
                this.setStudentLoginSuccessFlag(true);
            }
        }
    }

    public Student getStudent() {
        return student;
    }

    public Integer getLastEntryId(String filename) {
        List<Map<String, Object>> data = readJsonArrayFromFile(filename);
        if (!data.isEmpty()) {
            Map<String, Object> lastEntry = data.get(data.size() - 1);
            return (Integer) lastEntry.get("id");
        }
        return null;
    }

    static {
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }
}