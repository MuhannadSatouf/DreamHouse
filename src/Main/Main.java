package Main;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        Parent root = FXMLLoader.load(getClass().getResource("/View/logIn.fxml"));
        primaryStage.setTitle("Register");
        Scene scene=new Scene (root);
        scene.getStylesheets ().add(getClass ().getResource ("/Resources/Table.Css").toExternalForm ());
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
