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
import model.bookedTime.BookedTime;
import model.bookedTime.DateTime;
import model.rooms.Room;
import model.rooms.Rooms;
import model.schedule.Lesson;

import java.sql.Time;
import java.util.ArrayList;
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
    @FXML private ListView<Room> availableRooms;
    private BookedTime bookedTime;

    private ObservableList roomsViewModel;





    public void init(ViewHandler viewHandler, SchedulesModel model, Region root) {
        this.viewHandler=viewHandler;
        this.model = model;
        this.root = root;
        bookedTime = null;
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
        roomsViewModel = FXCollections.observableArrayList();
        availableRooms.setItems(roomsViewModel);


    }
    public Region getRoot(){
        return root;
    }

    public void reset(){
        bookedTime = null;
        roomsViewModel.clear();
    }


    public void backButton(ActionEvent actionEvent) {
        viewHandler.openView("chooseCourse.fxml");

    }

    public void saveButton(ActionEvent actionEvent) {
        if(bookedTime != null && availableRooms.getSelectionModel().getSelectedItem() != null){
            Lesson lesson = new Lesson(model.getSelectedData().getCourse(),model.getSelectedData().getSelectedTeacher(),availableRooms.getSelectionModel().getSelectedItem().getRoomId(),bookedTime);
            model.getSchedules().getScheduleById(model.getSelectedData().getSchedule().getId()).scheduleNewLesson(lesson);
            model.getStorage().rooms.getRoomByRoomId(lesson.getRoomId()).getBookedDates().add(bookedTime);
            model.getStorage().teachers.getTeacherByShortName(lesson.getTeacher()).addTeachingHours(bookedTime);
            Alert a = new Alert( Alert.AlertType.INFORMATION);
            a.setOnCloseRequest((e)->{
                viewHandler.openView("bookRoomForLesson.fxml");
            });
            a.setContentText("lesson Added  Successfully!!");
            a.show();
        }
        System.out.println(model.getSchedules());
        System.out.println(model.getStorage().teachers.getTeacherByShortName(model.getSelectedData().getSelectedTeacher()).getOccupiedTime());

    }
    @FXML
    public void endTime(ActionEvent actionEvent) {
        Double end = Double.parseDouble(endTime.getValue().toString().split(":")[0]) + Double.parseDouble(endTime.getValue().toString().split(":")[1])/100;
        Double start = Double.parseDouble(startTime.getValue().toString().split(":")[0]) + Double.parseDouble(startTime.getValue().toString().split(":")[1])/100;
        System.out.println(end);
        if(end <= start){
            Alert a = new Alert(ERROR);
            a.getButtonTypes().add(ButtonType.CLOSE);
            a.setOnCloseRequest((e)->{
                endTime.requestFocus();
            });
            a.setContentText("This end date is before start Time");
            a.show();

        }

    }
    @FXML
    public void endTimeCheck() {


    }

    public void endDate(ActionEvent actionEvent) {
    }

    public void checkAvailablityButton(ActionEvent actionEvent) {
        roomsViewModel.clear();
        if (startDate.getValue() == null || startTime.getValue() == null || endTime.getValue() == null) {
            System.out.println("null if");
        } else {
            Double end = Double.parseDouble(endTime.getValue().toString().split(":")[0]) + Double.parseDouble(endTime.getValue().toString().split(":")[1])/100;
            Double start = Double.parseDouble(startTime.getValue().toString().split(":")[0]) + Double.parseDouble(startTime.getValue().toString().split(":")[1])/100;
            if(end <= start){
                Alert a = new Alert(ERROR);
                a.getButtonTypes().add(ButtonType.CLOSE);
                a.setOnCloseRequest((e)->{
                    endTime.requestFocus();
                });
                a.setContentText("This end Time is before start Time");
                a.show();

            }else {
                 bookedTime = new BookedTime(
                        new DateTime(
                                startDate.getValue().getDayOfMonth(),
                                startDate.getValue().getMonthValue(),
                                startDate.getValue().getYear(),
                                (int) Math.floor(start),
                                (int)Double.parseDouble(startTime.getValue().toString().split(":")[1])
                                ),
                        new DateTime(
                                startDate.getValue().getDayOfMonth(),
                                startDate.getValue().getMonthValue(),
                                startDate.getValue().getYear(),
                                (int) Math.floor(end),
                                (int)Double.parseDouble(endTime.getValue().toString().split(":")[1])
                        ));
                ArrayList<Room> rooms = model.getStorage().rooms.getAvailableRooms(bookedTime);
                roomsViewModel.addAll(rooms);
            }
        }
        if(startDate.getValue() == null){
            startDate.requestFocus();
        }
        if(startTime.getValue() == null){
            startTime.requestFocus();
        }
        if(endTime.getValue() == null){
            endTime.requestFocus();
        }

    }

    public void startTime(ActionEvent actionEvent) {
        System.out.println(startTime.getValue());
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
