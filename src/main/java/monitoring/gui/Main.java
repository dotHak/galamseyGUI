package monitoring.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author  Lisa, Jones, Bobie, Hubert
 * @version 2.0.0
 */
public class Main extends Application {

    /**
     *  Start method to start the program
     * @param primaryStage the main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/masterView.fxml"));
        primaryStage.setTitle("Monitoring GUI");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    /**
     * Main method for execution of program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

