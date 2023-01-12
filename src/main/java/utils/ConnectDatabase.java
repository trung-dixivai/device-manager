package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public final static String DB_NAME = "QuanLyThetBiCoDinhNhaTruong";
    public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public final static String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME+";encrypt=true;trustServerCertificate=true";
    public final static String USER_NAME = "sa";
    public final static String PASSWORD = "1";


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println(" Kết Nối Thành Công ");
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lỗi Kết Nối");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
