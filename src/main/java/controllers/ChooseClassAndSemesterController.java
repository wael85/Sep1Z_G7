package controllers;
import factories.ViewHandler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableLongValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.SchedulesModel;
import model.courses.Course;
import model.schedule.Schedule;

import java.util.ArrayList;
import java.util.EventListener;


public class ChooseClassAndSemesterController {
    @FXML private ListView<String> semesterList;
    @FXML private ListView<String> classList;
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;

    private ArrayList<String> semesters;
    private ArrayList<String> classes;


    public ChooseClassAndSemesterController(){};

    public void init(ViewHandler viewHandler, SchedulesModel model, Region root) {
        this.model = model;
        this.viewHandler=viewHandler;
        this.root = root;
        semesters = new ArrayList<>();
        classes = new ArrayList<>();


        for (Course x:model.getStorage().courses.getCoursesList() ) {
            if(semesters.indexOf(x.getSemester()) == -1){
                semesters.add(x.getSemester());
            }
        }
        semesterList.getItems().addAll(semesters);
        semesterList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String selectedItem = semesterList.getSelectionModel().getSelectedItem();
                classList.getItems().clear();
                classes.clear();
                for (Course x:model.getStorage().courses.getCoursesList() ) {
                    if(x.getSemester().equals(selectedItem) && classes.indexOf(x.getClassID()) == -1){
                        classes.add(x.getClassID());
                    }
                }
                classList.getItems().addAll(classes);
            }
        });
    }
    public void reset(){
        //
    }

    public Region getRoot(){
        return root;
    }
    @FXML
    public void semestreClassNext() {
        String s = semesterList.getSelectionModel().getSelectedItem() + classList.getSelectionModel().getSelectedItem();
       if(model.getSchedules().getScheduleById(s) != null){
           Alert a = new Alert(Alert.AlertType.INFORMATION);
           a.setHeaderText("This schedule with id: "+s+" is already exist.");
           a.setContentText(" Do you want edit it?");
           a.getButtonTypes().addAll(ButtonType.NO);
           a.showAndWait().ifPresent(e ->{
               if(e == ButtonType.OK){
                  model.getSelectedData().setSchedule(model.getSchedules().getScheduleById(s));
                  viewHandler.openView("editSchedule.fxml");
               }else {

                   viewHandler.openView("chooseClassAndSemester.fxml");
               }
           });
       }else {
           Schedule schedule = new Schedule(s,semesterList.getSelectionModel().getSelectedItem(),classList.getSelectionModel().getSelectedItem());
           model.saveSchedule(schedule);
           model.getSelectedData().setSchedule(schedule);
           model.getSelectedData().setSemester(semesterList.getSelectionModel().getSelectedItem());
           model.getSelectedData().setClassName(classList.getSelectionModel().getSelectedItem());

           viewHandler.openView("chooseCourse.fxml");
       }
    }
    @FXML
    public void semestreClassBack(ActionEvent actionEvent) {
       viewHandler.openView("scheduleManagement.fxml");
    }

}
