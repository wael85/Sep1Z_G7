package controllers;

import factories.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.SchedulesModel;

import javafx.event.ActionEvent;
import model.person.Student;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.awt.*;

public class CreateLessonController{
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    @FXML private ListView studentsList;
    @FXML private ListView teachersList;
    @FXML private Label etcsLable;
    private ObservableList<Student> studentsViewModel;
    private ObservableList<String> teachersViewModel;
    @FXML private TextField studentName;
    @FXML private TextField surname;
    @FXML private TextField studentNr;


    public void init(ViewHandler viewHandler, SchedulesModel model, Region root ) {
        this.root = root;
        this.model = model;
        this.viewHandler=viewHandler;


        studentsViewModel = FXCollections.observableArrayList();
        teachersViewModel = FXCollections.observableArrayList();

        for (String x:model.getSelectedData().getCourse().getTeachers() ) {
            teachersViewModel.add(x);
        }
        teachersList.setItems(teachersViewModel);
        for (Student x : model.getSelectedData().getCourse().getStudents().getStudents()){
            studentsViewModel.add(x);
        }
        studentsList.setItems(studentsViewModel);
        etcsLable.setText(model.getSelectedData().getCourse().getECTS());
    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }
    public void reset(){
        //
    }

    public void setViewHandler(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public SchedulesModel getModel() {
        return model;
    }

    public void setModel(SchedulesModel model) {
        this.model = model;
    }

    public javafx.scene.layout.Region getRoot() {
        return root;
    }

    public void setRoot(Region root) {
        this.root = root;
    }

    public void bookRoomButton(ActionEvent actionEvent) {
    }

    public void backButton(ActionEvent actionEvent) {
    }

    public void nameField(ActionEvent actionEvent) {
    }

    public void surnameFiels(ActionEvent actionEvent) {
    }
    public void studentNumberField(ActionEvent actionEvent) {

    }
    public void addStudentButton(ActionEvent actionEvent) {
    }

    public void deleteStudentButton(ActionEvent actionEvent) {
    }


}
