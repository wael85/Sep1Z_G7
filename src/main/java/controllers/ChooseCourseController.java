package controllers;

import factories.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import model.SchedulesModel;

public class ChooseCourseController {
    private Region root;
    private SchedulesModel model;
    private ViewHandler viewHandler;

    public ChooseCourseController(){};
    public void init(ViewHandler viewHandler, SchedulesModel model, Region root) {
        this.viewHandler=viewHandler;
        this.root= root;
        this.model = model;
    }
    public void reset(){
        //
    };
    public Region getRoot(){
        return root;
    }

    public void CourseNext(ActionEvent actionEvent) {
        viewHandler.openView("chooseClassAndSemester.fxml");
    }

    public void courseBack(ActionEvent actionEvent) {
        viewHandler.openView("chooseClassAndSemester.fxml");
    }
}
