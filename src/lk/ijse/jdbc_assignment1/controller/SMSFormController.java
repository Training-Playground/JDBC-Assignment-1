package lk.ijse.jdbc_assignment1.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.jdbc_assignment1.tm.StudentTM;

import java.sql.*;
import java.util.Locale;

public class SMSFormController {
    public Button btnClear;
    public Label lblStudentID;
    public TextField txtName;
    public TextField txtContact;
    public ListView<String> lstContacts;
    public Button btnRemove;
    public TableView<StudentTM> tblStudents;
    private Connection connection;
    private PreparedStatement pstmSaveStudent;
    private PreparedStatement pstmSaveContact;

    public void initialize() {
        lblStudentID.setText("Generated ID");
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<StudentTM, ListView<String>> colContacts = (TableColumn<StudentTM, ListView<String>>) tblStudents.getColumns().get(2);

        colContacts.setCellValueFactory(param -> {
            ListView<String> lstContacts = new ListView<>();
            lstContacts.setMaxHeight(75);
            return new ReadOnlyObjectWrapper<>(lstContacts);
        });
        TableColumn<StudentTM, Button> colDelete = (TableColumn<StudentTM, Button>) tblStudents.getColumns().get(3);

        colDelete.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        txtContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String contact = txtContact.getText();

                if (contact.trim().isEmpty()){
                    return;
                }

                lstContacts.getItems().add(contact);
                txtContact.clear();

            }
        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep7", "root", "mysql");
            pstmSaveStudent = connection.prepareStatement("INSERT INTO student (name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            pstmSaveContact = connection.prepareStatement("INSERT INTO contact (contact, student_id) VALUES (?,?);");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {

                try {

                    if (connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnRemove_OnAction(ActionEvent actionEvent) {
        if (lstContacts.getSelectionModel().isEmpty()) return;
        lstContacts.getItems().remove(lstContacts.getSelectionModel().getSelectedItem());
    }

    public void btnClear_OnAction(ActionEvent actionEvent) {
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        try {

            // Let's start to buffer
            connection.setAutoCommit(false);

            pstmSaveStudent.setString(1, txtName.getText());
            int affectedRows = pstmSaveStudent.executeUpdate();

            if (affectedRows != 1){
                throw new RuntimeException("Failed to save in the student table");
            }

            ResultSet generatedKeys = pstmSaveStudent.getGeneratedKeys();
            generatedKeys.next();

            for (String contact : lstContacts.getItems()) {
                pstmSaveContact.setString(1, contact);
                pstmSaveContact.setInt(2, generatedKeys.getInt(1));

                if (contact.contains("a")){
                    throw new RuntimeException("Invalid contact");
                }

                affectedRows = pstmSaveContact.executeUpdate();

                if (affectedRows != 1){
                    throw new RuntimeException("Failed to save the contact " + contact);
                }
            }

            connection.commit();
            // Buffer -> Flush
            // By default transaction apply (it doesn't buffer anymore)

            new Alert(Alert.AlertType.INFORMATION, "Student has been saved successfully").show();
            txtName.clear();
            txtContact.clear();
            lstContacts.getItems().clear();
            txtName.requestFocus();

        } catch (Throwable e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the student").show();
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
