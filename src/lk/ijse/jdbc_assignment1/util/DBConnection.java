package lk.ijse.jdbc_assignment1.util;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep7", "root", "Hamza@mysql56597");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (!connection.isClosed()){
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));

        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load the DBConnection").show();
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance(){
        return (dbConnection == null) ? dbConnection = new DBConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }

}
