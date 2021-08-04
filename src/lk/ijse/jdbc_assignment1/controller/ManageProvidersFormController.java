package lk.ijse.jdbc_assignment1.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.jdbc_assignment1.tm.ProviderTM;

import java.io.IOException;

public class ManageProvidersFormController {
    public TextField txtID;
    public TextField txtProvider;
    public Button btnSave;
    public TableView<ProviderTM> tblProviders;

    public void initialize(){
        tblProviders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblProviders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("provider"));
        TableColumn<ProviderTM, Button> colDelete = (TableColumn<ProviderTM, Button>) tblProviders.getColumns().get(2);

        colDelete.setCellValueFactory(param -> {
            Button btnDelete = new Button("Remove");
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });


    }

    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        HomeFormController.navigate(HomeFormController.NavigationMenu.HOME);
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }
}
