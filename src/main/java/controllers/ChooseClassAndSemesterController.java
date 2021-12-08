package controllers;
import factories.ViewHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.SchedulesModel;

public class ChooseClassAndSemesterController {
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;

    public ChooseClassAndSemesterController(){};

    public void init(ViewHandler viewHandler, SchedulesModel model, Region root) {
        this.model = model;
        this.viewHandler=viewHandler;
        this.root = root;
    }
    public void reset(){
        //
    }
    public Region getRoot(){
        return root;
    }
    @FXML
    public void semestreClassNext(ActionEvent actionEvent) {
        viewHandler.openView("chooseCourse.fxml");
    }
    @FXML
    public void semestreClassBack(ActionEvent actionEvent) {

    }
}
