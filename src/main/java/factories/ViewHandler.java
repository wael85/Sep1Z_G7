package factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import controllers.ChooseClassAndSemesterController;
import controllers.ChooseCourseController;
import controllers.ImportFilesController;
import controllers.ScheduleManagementController;

import java.io.IOException;
import java.util.Objects;
import model.SchedulesModel;

public class ViewHandler {
    private Scene currentScene;
    private SchedulesModel model;
    private ScheduleManagementController scheduleManagementController;
    private ImportFilesController importFilesController;
    private ChooseClassAndSemesterController chooseClassAndSemesterController;
    private ChooseCourseController chooseCourseController;
    private Stage mainStage;


    public ViewHandler(SchedulesModel model) {
        this.model = model;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage mainStage) {
       this.mainStage = mainStage;
       openView("scheduleManagement.fxml");
    }


    public void openView(String id){
        Region root = null;
        switch (id){
            case "scheduleManagement.fxml":
                root = loadScheduleManagement("/scheduleManagement.fxml");break;
            case "chooseCourse.fxml":
                root = loadChooseCourse("/chooseCourse.fxml");break;
            case "chooseClassAndSemester.fxml":
                root = openChooseClassAndSemester("/chooseClassAndSemester.fxml");break;
            case "importFiles.fxml":
                root = loadImportFile("/importFiles.fxml");break;
        }
        currentScene.setRoot(root);
        String title ="";
        if(root.getUserData() != null){
            title += root.getUserData();
        }
        mainStage.setTitle(title);
        mainStage.setScene(currentScene);
        mainStage.setWidth(root.getPrefWidth());
        mainStage.setHeight(root.getPrefHeight());
        mainStage.show();
    }
    public Region loadScheduleManagement(String fxmlFile){
        if(scheduleManagementController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation((getClass().getResource(fxmlFile)));
                Region root = loader.load();
                scheduleManagementController = loader.getController();
                scheduleManagementController.init(this,model,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            scheduleManagementController.reset();
        }
         return scheduleManagementController.getRoot();
    }
    public Region loadChooseCourse(String fxmlFile){
        if(chooseCourseController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Objects.requireNonNull(getClass().getResource(fxmlFile)));
                Region root = loader.load();
                chooseCourseController = loader.getController();
                chooseCourseController.init(this,model,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            chooseCourseController.reset();
        }
        return chooseCourseController.getRoot();
    }
    public Region loadImportFile(String fxmlFile){
        if(importFilesController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Objects.requireNonNull(getClass().getResource(fxmlFile)));
                Region root = loader.load();
                importFilesController = loader.getController();
                importFilesController.init(this,model,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            importFilesController.reset();
        }
        return importFilesController.getRoot();
    }
    public Region openChooseClassAndSemester(String fxmlFile){
        if(chooseClassAndSemesterController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Objects.requireNonNull(getClass().getResource(fxmlFile)));
                Region root = loader.load();
                chooseClassAndSemesterController = loader.getController();
                chooseClassAndSemesterController.init(this,model,root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            chooseClassAndSemesterController.reset();
        }
        return chooseClassAndSemesterController.getRoot();
    }
    public void closeView(){
        mainStage.close();
    }
 /*   public void openChooseClassAndSemester(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/chooseClassAndSemester.fxml"));
            Parent root = loader.load();
            ChooseClassAndSemesterController controller = loader.getController();
            controller.init(this);
            Scene scene = new Scene(root);
            mainStage.setTitle("School System");

            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openChooseCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseCourse.fxml"));
            //loader.setLocation();
            Parent root = loader.load();
            ChooseCourseController controller = loader.getController();
            controller.init(this);
            Scene scene = new Scene(root);
            mainStage.setTitle("School System");

            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openImportFiles() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("importFiles.fxml"));
           // loader.setLocation(getClass().getResource("i/mportFiles.fxml"));
            Parent root = loader.load();
            ChooseCourseController controller = loader.getController();
            controller.init(this);
            Scene scene = new Scene(root);
            mainStage.setTitle("School System");

            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

