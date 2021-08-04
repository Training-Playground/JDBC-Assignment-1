package lk.ijse.jdbc_assignment1.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ManageProvidersFormController {
    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        HomeFormController.navigate(HomeFormController.NavigationMenu.HOME);
    }
}
