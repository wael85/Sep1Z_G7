package controllers;

import factories.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.SchedulesModel;
import model.schedule.Lesson;
import model.schedule.Schedule;

public class CourseEditingPageController {
    public ListView listView;
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    private ObservableList<Lesson> observableList;



    public void init(ViewHandler viewHandler, SchedulesModel model,Region root) {

        this.viewHandler=viewHandler;
        this.model=model;
        this.root=root;
        observableList = FXCollections.observableArrayList();
        observableList.addAll(model.getSelectedSchedule().getLessons());
        listView.setItems(observableList);




    }

    public void editCourseBackButton(ActionEvent actionEvent) {
        viewHandler.openView("editSchedule.fxml");
    }

    public void addNewCourseButton(ActionEvent actionEvent) {
    }


    public void reset() {
    }

    public Region getRoot() {
        return root;
    }
}
