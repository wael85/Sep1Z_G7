import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        try {
            Application.launch(App.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
