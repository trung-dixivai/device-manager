package migrations;

import utils.ConnectDatabase;

import java.sql.Connection;

public class MainMigration {
    Connection connection = ConnectDatabase.connection;
    public MainMigration() {
        new CategoryMigration(connection);
        new StatusMigration(connection);
        new DeviceMigration(connection);
    }
    public static void main(String[] args) {
        new MainMigration();
    }
}
