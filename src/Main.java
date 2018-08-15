import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public  void  init() {
        System.out.println("Inicjalizuję okno aplikacji");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel logowania");
        primaryStage.show();
    }
    @Override
    public void stop(){
        System.out.println("Zamykam program!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
