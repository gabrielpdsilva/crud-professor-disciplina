package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TabPane tabPane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle("Gerenciamento Escolar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
