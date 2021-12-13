package controllers;

import factories.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.SchedulesModel;

import javafx.scene.layout.Region;

public class EditCoursePageController {
    public TextField TextFileName;
    public TextField TextFileSurname;
    public TextField TextFileStudentNumber;
    public Button addStudent;
    public Button deleteStudent;
    public ListView listStudents;
    public ListView listTeachers;
    public ListView listLessons;
    public Button deleteLessons;
    public Button createNewLesson;
    public Button goBack;
    public Button save;
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    public void init(ViewHandler viewHandler,SchedulesModel model,Region root) {
        this.viewHandler=viewHandler;
        this.model=model;
        this.root=root;
    }

    public void addStudentButton(ActionEvent actionEvent) {
    }

    public void deleteStudentButton(ActionEvent actionEvent) {
    }

    public void deleteLessonButton(ActionEvent actionEvent) {
    }

    public void createNewLessonButton(ActionEvent actionEvent) {
    }

    public void backButton(ActionEvent actionEvent) {
    }

    public void saveButton(ActionEvent actionEvent) {
    }

    public void reset() {
    }

    public Region getRoot() {
        return root;
    }
}
