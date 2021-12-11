package controllers;

import factories.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.SchedulesModel;
import model.courses.Course;
import model.courses.Courses;
import model.schedule.Schedule;

import java.util.ArrayList;

public class ChooseCourseController {
    @FXML private ListView<String> courseList;
    private Region root;
    private SchedulesModel model;
    private ViewHandler viewHandler;
    private ArrayList<String> coursesArray;


    public ChooseCourseController(){};
    public void init(ViewHandler viewHandler, SchedulesModel model, Region root) {
        this.viewHandler=viewHandler;
        this.root= root;
        this.model = model;
        coursesArray = new ArrayList<>();

        Schedule schedule = model.getSelectedData().getSchedule();
        coursesArray.clear();
        courseList.getItems().clear();
        for (Course x: model.getStorage().courses.getCoursesList()) {
            String s =x.getSemester()+x.getClassID();
            if (model.getSelectedData().getSchedule().getId().equals(s)){
                if(!(coursesArray.contains(x.getCourseName()))){
                    coursesArray.add(x.getCourseName());
                }
            }
        }
        courseList.getItems().addAll(coursesArray);
    }
    public void reset(){
        //
    };
    public Region getRoot(){
        return root;
    }

    public void CourseNext() {
        for (int i = 0; i < model.getStorage().courses.getCoursesList().size(); i++) {
            if(
                    model.getStorage().courses.getCoursesList().get(i).getClassID().equals(model.getSelectedData().getClassName()) &&
                    model.getStorage().courses.getCoursesList().get(i).getSemester().equals(model.getSelectedData().getSemester() )&&
                    model.getStorage().courses.getCoursesList().get(i).getCourseName().equals(courseList.getSelectionModel().getSelectedItem())
            ){
                model.getSelectedData().setCourse(model.getStorage().courses.getCoursesList().get(i));
            }
        }


        //model.getSelectedData().setCourse();
        viewHandler.openView("createLesson.fxml");
    }

    public void courseBack(ActionEvent actionEvent) {
        viewHandler.openView("chooseClassAndSemester.fxml");
    }
}
