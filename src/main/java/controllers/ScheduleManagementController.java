
package controllers;

import factories.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.SchedulesModel;

public class ScheduleManagementController {

    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;

    public ScheduleManagementController(){};

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
    public void createSchedule(ActionEvent actionEvent) {
        viewHandler.openView("chooseClassAndSemester.fxml");

    }
    @FXML
    public void editSchedule() {
       viewHandler.openView("editSchedule.fxml");
    }

    @FXML
    public void importFiles(ActionEvent actionEvent) {
        viewHandler.openView("importFiles.fxml");
    }
}
