package controllers;

import factories.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.SchedulesModel;
import model.rooms.Rooms;

import java.sql.Time;
import java.util.Calendar;

public class BookRoomForLessonController {
    private Alert.AlertType ERROR ;
    private ViewHandler viewHandler;
    private SchedulesModel model;
    private Region root;
    @FXML private ComboBox startTime;
    @FXML private ComboBox endTime;
    @FXML private DatePicker startDate;
    @FXML private  DatePicker endPeriod;
    @FXML private ListView availableRooms;

    private ObservableList roomsViewModel;
    private Rooms roomsModel;





    public void init(ViewHandler viewHandler, SchedulesModel model, Region root) {
        this.viewHandler=viewHandler;
        this.model = model;
        this.root = root;
        this.ERROR = null;
        // get all time between 8 and 16
        ObservableList timeList = FXCollections.observableArrayList();
        for (int i = 8; i < 16; i++) {
            for (int j = 0; j < 59; j+= 15) {
                String s = i + " : ";
                if (i<10){
                     s = "0"+i + " : ";
                }
                if(j == 0){
                    s+="00";
                    timeList.add(s);
                }else {
                    s+= j;
                    timeList.add(s);
                }

            }
        }
        startTime.getItems().addAll(timeList);
        endTime.getItems().addAll(timeList);

    }
    public Region getRoot(){
        return root;
    }
    public void reset(){
        //
    }

    public void backButton(ActionEvent actionEvent) {

    }

    public void saveButton(ActionEvent actionEvent) {
    }

    public void endTime(ActionEvent actionEvent) {
    }

    public void endDate(ActionEvent actionEvent) {
    }

    public void checkAvailablityButton(ActionEvent actionEvent) {
    }

    public void startTime(ActionEvent actionEvent) {
    }

    public void startDate(ActionEvent actionEvent) {
    }

    public void endPeriodTime(ActionEvent actionEvent) {
    }

    public void endPeriodDate(ActionEvent actionEvent) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, endPeriod.getValue().getDayOfMonth());
        cal.set(Calendar.MONTH, endPeriod.getValue().getMonthValue());
        cal.set(Calendar.YEAR, endPeriod.getValue().getYear());
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_MONTH, startDate.getValue().getDayOfMonth());
        cal1.set(Calendar.MONTH, startDate.getValue().getMonthValue());
        cal1.set(Calendar.YEAR, startDate.getValue().getYear());
        if(cal.getTimeInMillis() < cal1.getTimeInMillis()){
            Alert a = new Alert(ERROR);
            a.getButtonTypes().add(ButtonType.CLOSE);
            a.setOnCloseRequest((e)->{
                endPeriod.requestFocus();
            });
            a.setContentText("This end date is before start Date");
            a.show();

       }
    }
}
