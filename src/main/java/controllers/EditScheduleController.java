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
    private Schedules schedulesList;
    public void init(ViewHandler viewHandler, SchedulesModel model,Region root) {
        this.viewHandler=viewHandler;
        this.model=model;
        this.root = root;

        schedulesList = model.getSchedules();
        scheduleViewModelList = FXCollections.observableArrayList();
        if(schedulesList.getSchedules().size()>0){
            scheduleViewModelList.addAll(schedulesList.getSchedules());
        }
        schedulesViewList.getItems().addAll(scheduleViewModelList);




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
        //
    }


    public void list() {
        schedulesViewList.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {

                //Use ListView's getSelected Item
                Schedule schedule = schedulesViewList.getSelectionModel().getSelectedItem();
                model.setSelectedSchedule(schedule);
                viewHandler.openView("courseEditingPage.fxml");
            }
        });


    }

    public void selected() {

    }
}
