package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

//    private double xAxis = 0;
//    private double yAxis = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1232, 839);
        scene.setFill(null);
        primaryStage.setScene(scene);
//        root.setStyle("-fx-background-color: transparent;");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
