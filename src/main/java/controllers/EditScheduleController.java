package controllers;

import factories.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import model.SchedulesModel;

import javafx.scene.layout.Region;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.schedule.Schedule;
import model.schedule.Schedules;

public class EditScheduleController {
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    @FXML private ListView<Schedule> schedulesViewList;
    private ObservableList scheduleViewModelList;
    public void init(ViewHandler viewHandler, SchedulesModel model,Region root) {
        this.viewHandler=viewHandler;
        this.model=model;
        this.root = root;


        scheduleViewModelList = FXCollections.observableArrayList();
        for (Schedule s: model.getSchedules().getSchedules()){
            scheduleViewModelList.add(s);
        }
        schedulesViewList.setItems(scheduleViewModelList);
    }


    public void editScheduleBack(ActionEvent actionEvent) {
        viewHandler.openView("scheduleManagement.fxml");
    }

    public void deleteButton() {
        model.deleteSchedule(schedulesViewList.getSelectionModel().getSelectedItem());


//Model
        scheduleViewModelList.remove(schedulesViewList.getSelectionModel().getSelectedItem());

//Model for the actual view
        schedulesViewList.getItems().remove(schedulesViewList.getSelectionModel().getSelectedItem());



    }


    public Region getRoot() {
        return root;
    }

    public void reset() {

        scheduleViewModelList.clear();
        for (Schedule s: model.getSchedules().getSchedules()){
            scheduleViewModelList.add(s);
        }
    }


    public void list() {
        schedulesViewList.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {
                model.getSelectedData().setSchedule(schedulesViewList.getSelectionModel().getSelectedItem());
                model.getSelectedData().setSemester(schedulesViewList.getSelectionModel().getSelectedItem().getSemester());
                model.getSelectedData().setClassName(schedulesViewList.getSelectionModel().getSelectedItem().getClassName());
                //Use ListView's getSelected Item
               // Schedule schedule = schedulesViewList.getSelectionModel().getSelectedItem();
               // model.setSelectedSchedule(schedule);
                viewHandler.openView("editCoursePage.fxml");
            }
        });


    }

    public void selected() {

    }
}
