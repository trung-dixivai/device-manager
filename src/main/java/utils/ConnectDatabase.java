package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public static Connection connection = getConnection();


    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/qlthietbi";
            String DB_NAME = "qlthietbi";
            String USER_NAME = "root";
            String USER_PASSWORD = "namnam";
            conn = DriverManager.getConnection(url, USER_NAME, USER_PASSWORD);
            System.out.println("Connected to the MySQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}