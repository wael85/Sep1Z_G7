import factories.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ScheduleModelManger;
import model.SchedulesModel;


public class App extends Application  {
    @Override
    public void start(Stage mainStage)  {
        System.out.println(mainStage.getX());
        SchedulesModel model = new ScheduleModelManger();
        ViewHandler viewHandler= new ViewHandler(model);
        try {
            viewHandler.start(mainStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

