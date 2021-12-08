package controllers;

import factories.*;
import javafx.scene.layout.Region;
import model.SchedulesModel;

public class ImportFilesController {
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;

    public ImportFilesController(){};

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

}
