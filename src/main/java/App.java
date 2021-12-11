import factories.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ScheduleModelManger;
import model.SchedulesModel;
import model.courses.Course;


public class App extends Application  {
    @Override
    public void start(Stage mainStage)  {
        System.out.println(mainStage.getX());
        SchedulesModel model = new ScheduleModelManger();
        ViewHandler viewHandler= new ViewHandler(model);
        try {
            model.fetchStudents("C:\\Users\\drwae\\Downloads\\Students.txt");
            model.fetchTeachersAndCourses("C:\\Users\\drwae\\Downloads\\Courses.txt");
            model.fetchRooms("C:\\Users\\drwae\\Downloads\\Rooms.txt");
            for (Course x: model.getStorage().courses.getCoursesList()) {
                for (String y:x.getTeachers()) {
                    System.out.println(x.getSemester() +" "+ x.getClassID()+" "+ x.getCourseName()+" "+ y);
                }

            }
            viewHandler.start(mainStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

