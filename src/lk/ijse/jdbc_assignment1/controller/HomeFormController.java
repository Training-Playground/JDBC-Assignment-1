package lk.ijse.jdbc_assignment1.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.jdbc_assignment1.AppInitializer;

import java.io.IOException;

public class HomeFormController {
    public AnchorPane root;

    public enum NavigationMenu {
        STUDENTS, PROVIDERS, HOME
    }

    public void btnManageStudents_OnAction(ActionEvent actionEvent) throws IOException {
        navigate(NavigationMenu.STUDENTS);
    }

    public void btnManageProviders_OnAction(ActionEvent actionEvent) throws IOException {
        navigate(NavigationMenu.PROVIDERS);
    }

    public static void navigate(NavigationMenu navigationMenu) throws IOException {

        String url = "";

        switch (navigationMenu) {
            case STUDENTS:
                url = "../view/ManageStudentsForm.fxml";
                break;
            case PROVIDERS:
                url = "../view/ManageProvidersForm.fxml";
                break;
            default:
                url = "../view/HomeForm.fxml";
                break;
        }

        AnchorPane root = FXMLLoader.load(HomeFormController.class.getResource(url));
        AppInitializer.getPrimaryStage().setScene(new Scene(root));
        Platform.runLater(() -> {
            AppInitializer.getPrimaryStage().sizeToScene();
            AppInitializer.getPrimaryStage().centerOnScreen();
        });
    }
}
