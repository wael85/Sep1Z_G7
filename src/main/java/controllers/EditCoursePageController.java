package controllers;

import factories.ViewHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.SchedulesModel;

import javafx.scene.layout.Region;
import model.bookedTime.BookedTime;
import model.courses.Course;
import model.courses.Courses;
import model.person.Student;
import model.person.Teacher;
import model.schedule.Lesson;

import java.util.ArrayList;

public class EditCoursePageController {
    @FXML private TextField studentName;
    @FXML private TextField surname;
    @FXML private TextField studentNr;
   @FXML private  TextField TextFileShortName1;
    @FXML private ListView listStudents;
          private ObservableList studentsViewModel;

    @FXML private ListView<String> listTeachers;
    private ObservableList<String> teachersViewModel;

    @FXML private ListView<Lesson> listLessons;
    private ObservableList<Lesson> lessonsViewModel;

    @FXML private ComboBox<String> coursesBox;
    private ObservableList<String> coursesViewModel;

    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    public void init(ViewHandler viewHandler,SchedulesModel model,Region root) {
        this.viewHandler=viewHandler;
        this.model=model;
        this.root=root;
        teachersViewModel = FXCollections.observableArrayList();
        studentsViewModel = FXCollections.observableArrayList();
        coursesViewModel = FXCollections.observableArrayList();
        lessonsViewModel =  FXCollections.observableArrayList();
        coursesViewModel.clear();
        for (Course x: model.getStorage().courses.getCoursesList()) {
            if(x.getClassID().equals(model.getSelectedData().getSchedule().getClassName()) &&
               x.getSemester().equals(model.getSelectedData().getSchedule().getSemester())
            ){
                coursesViewModel.add(x.getCourseName());

            }
        }
        coursesBox.setItems(coursesViewModel);

    }
    public void reset() {
        teachersViewModel.clear();
        studentsViewModel.clear();
        lessonsViewModel.clear();
        coursesViewModel.clear();
        for (Course x: model.getStorage().courses.getCoursesList()) {
            if(
                    x.getClassID().equals(model.getSelectedData().getSchedule().getClassName()) &&
                    x.getSemester().equals(model.getSelectedData().getSchedule().getSemester())
            ){
                coursesViewModel.add(x.getCourseName());

            }
        }
        coursesBox.setItems(coursesViewModel);
    }

    public Region getRoot() {
        return root;
    }


   @FXML
   public void loadAll(){
       for (Course x: model.getStorage().courses.getCoursesList()) {
           if(
                   x.getCourseName().equals(coursesBox.getValue()) &&
                   x.getSemester().equals(model.getSelectedData().getSemester()) &&
                   x.getClassID().equals(model.getSelectedData().getClassName())
           ){
               model.getSelectedData().setCourse(x);
           }
       }
       teachersViewModel.clear();
       for (String x:model.getSelectedData().getCourse().getTeachers() ) {
           teachersViewModel.add(x);
       }
       listTeachers.setItems(teachersViewModel);
       studentsViewModel.clear();
       for (Student x : model.getSelectedData().getCourse().getStudents().getStudents()){
           studentsViewModel.add(x);
       }
       listStudents.setItems(studentsViewModel);

       // load lessons for this course
       lessonsViewModel.clear();
       for (Lesson l : model.getSelectedData().getSchedule().getLessons()) {
           if(l.getCourse().getCourseName().equals(coursesBox.getValue())){
               lessonsViewModel.add(l);
           }
       }
       listLessons.setItems(lessonsViewModel);


   }
    public void addStudentButton(ActionEvent actionEvent) {
        if(studentName.getText() == ""){
            studentName.requestFocus();
        }
        if(surname.getText() == ""){
            surname.requestFocus();
        }else {
            Student student = new Student(studentName.getText(),surname.getText(),surname+"@via.dk","23423",model.getSelectedData().getClassName(),model.getSelectedData().getSemester(),studentNr.getText());
            model.getSelectedData().getCourse().getStudents().getStudents().add(student);
            studentsViewModel.clear();
            for (Student x : model.getSelectedData().getCourse().getStudents().getStudents()){
                studentsViewModel.add(x);
            }
            studentName.setText("");
            surname.setText("");
            studentNr.setText("");
        }
    }

    public void deleteStudentButton(ActionEvent actionEvent) {
        for (int i = 0; i < model.getSelectedData().getCourse().getStudents().getStudents().size(); i++) {
            if ( model.getSelectedData().getCourse().getStudents().getStudents().get(i).equals(listStudents.getSelectionModel().getSelectedItem())){
                model.getSelectedData().getCourse().getStudents().getStudents().remove(i);
            }
        }
        studentsViewModel.clear();
        for (Student x : model.getSelectedData().getCourse().getStudents().getStudents()){
            studentsViewModel.add(x);
        }
    }

    public void deleteLessonButton(ActionEvent actionEvent) {
        for (int i = 0; i < model.getSelectedData().getSchedule().getLessons().size(); i++) {
            if(model.getSelectedData().getSchedule().getLessons().get(i).equals(listLessons.getSelectionModel().getSelectedItem())){
              // remove lesson booked time from teacher occupied Time
                for (BookedTime t: model.getStorage().teachers.getTeacherByShortName( model.getSelectedData().getSchedule().getLessons().get(i).getTeacher()).getOccupiedTime()) {
                    if(t.equals(model.getSelectedData().getSchedule().getLessons().get(i).getReservedDateTime())){
                        model.getStorage().teachers.getTeacherByShortName( model.getSelectedData().getSchedule().getLessons().get(i).getTeacher()).getOccupiedTime().remove(t);
                    }

                }
                // remove the lesson and mark the room as available
                for (int j = 0; j < model.getStorage().rooms.getRoomByRoomId(listLessons.getSelectionModel().getSelectedItem().getRoomId()).getBookedDates().size(); j++) {
                    if(model.getStorage().rooms.getRoomByRoomId(listLessons.getSelectionModel().getSelectedItem().getRoomId()).getBookedDates().get(j).equals(listLessons.getSelectionModel().getSelectedItem())){
                        model.getStorage().rooms.getRoomByRoomId(listLessons.getSelectionModel().getSelectedItem().getRoomId()).getBookedDates().remove(j);
                    }
                }
                model.getSelectedData().getSchedule().getLessons().remove(i);
            }
        } lessonsViewModel.clear();
        for (Lesson l : model.getSelectedData().getSchedule().getLessons()) {
            if(l.getCourse().getCourseName().equals(coursesBox.getValue())){
                lessonsViewModel.add(l);
            }
        }
        listLessons.setItems(lessonsViewModel);


    }

    public void createNewLessonButton(ActionEvent actionEvent) {
        if(listTeachers.getSelectionModel().getSelectedItem() == null){
            listTeachers.requestFocus();
        }else {
            model.getSelectedData().setSelectedTeacher(listTeachers.getSelectionModel().getSelectedItem());
            viewHandler.openView("bookRoomForLesson.fxml");
        }

    }

    public void backButton(ActionEvent actionEvent) {
        viewHandler.openView("editSchedule.fxml");
    }

    public void saveButton(ActionEvent actionEvent) {
    }
    @FXML
    public void addTeacherButton(ActionEvent actionEvent) {
        if(model.getStorage().teachers.getTeacherByShortName(TextFileShortName1.getText()) == null){
            Teacher t = new Teacher(TextFileShortName1.getText(),"firstName","LastName", TextFileShortName1.getText()+"@.via","29920518");
            model.getStorage().teachers.addTeacher(t);
            model.getSelectedData().getCourse().getTeachers().add(t.getShortName());
        }else {
            model.getSelectedData().getCourse().getTeachers().add(TextFileShortName1.getText());
        }
        teachersViewModel.clear();
        for (String x:model.getSelectedData().getCourse().getTeachers() ) {
            teachersViewModel.add(x);
        }
        TextFileShortName1.clear();
    }
    @FXML
    public void deleteTeacher(){
        for (int i = 0; i < model.getSelectedData().getCourse().getTeachers().size(); i++) {
           if(model.getSelectedData().getCourse().getTeachers().get(i).equals(listTeachers.getSelectionModel().getSelectedItem())){
               model.getSelectedData().getCourse().getTeachers().remove(i);
           }
        }
        teachersViewModel.clear();
        for (String x:model.getSelectedData().getCourse().getTeachers() ) {
            teachersViewModel.add(x);
        }
    }




}
