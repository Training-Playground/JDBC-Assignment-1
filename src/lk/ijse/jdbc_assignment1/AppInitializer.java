package lk.ijse.jdbc_assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.jdbc_assignment1.controller.HomeFormController;

import java.io.IOException;

public class AppInitializer extends Application {

    private static Stage primaryStage = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AppInitializer.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(this.getClass().getResource("view/HomeForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("JDBC Assignment - 1");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
}
