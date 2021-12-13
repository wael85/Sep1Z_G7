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
    @FXML
    public void bookRoomButton() {
        System.out.println("here1");
        model.getSelectedData().getCourse().getStudents().getStudents().clear();
        model.getSelectedData().getCourse().getStudents().getStudents().addAll(studentsViewModel);
        viewHandler.openView("bookRoomForLesson.fxml");

        System.out.println("here 2");
    }

    public void backButton(ActionEvent actionEvent) {
    }

    public void nameField(ActionEvent actionEvent) {
    }

    public void surnameFiels(ActionEvent actionEvent) {
    }
    public void studentNumberField(ActionEvent actionEvent) {

    }
    public void addStudentButton() {
        if(studentName.getText() == ""){
            studentName.requestFocus();
        }
        if(surname.getText() == ""){
            surname.requestFocus();
        }else {
            Student student = new Student(studentName.getText(),surname.getText(),surname+"@via.dk","23423",model.getSelectedData().getClassName(),model.getSelectedData().getSemester(),studentNr.getText());
            studentsViewModel.add(student);
            studentName.setText("");
            surname.setText("");
            studentNr.setText("");
        }


    }

    public void deleteStudentButton(ActionEvent actionEvent) {
        for (int i = 0; i < studentsViewModel.size(); i++) {
            if (studentsViewModel.get(i).equals(studentsList.getSelectionModel().getSelectedItem())){
                studentsViewModel.remove(i);
            }
        }
    }


}
