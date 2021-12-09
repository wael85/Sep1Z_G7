package controllers;

import factories.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.SchedulesModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.File;

public class ImportFilesController {
    @FXML private Label errorLabel;
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    private Window window;

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

    @FXML
    public void importRoomsFiles(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                //new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                //new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(window);

        try {
           model.fetchRooms(selectedFile.getPath());
            Alert a = new Alert( Alert.AlertType.INFORMATION);
            a.setContentText("Rooms Uploaded Successfully!!");
            a.show();
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }

    }
    @FXML
    public void importStudentsFiles(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        //new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
        //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
        //new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(window);
        try {
            model.fetchStudents(selectedFile.getPath());
            Alert a = new Alert( Alert.AlertType.INFORMATION);
            a.setContentText("Students Uploaded Successfully!!");
            a.show();
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML
    public void importCoursesFiles(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        //new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
        //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
        //new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(window);
        try {
            model.fetchTeachersAndCourses(selectedFile.getPath());
            Alert a = new Alert( Alert.AlertType.INFORMATION);
            a.setContentText("Courses And Teachers Uploaded Successfully!!");
            a.show();
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void goBack(){
        try {
            viewHandler.openView("scheduleManagement.fxml");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
