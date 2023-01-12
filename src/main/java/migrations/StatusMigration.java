package migrations;

import java.sql.Connection;

import static utils.ConnectDatabase.getConnection;

public class StatusMigration {


    String sql = "CREATE TABLE status ( id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, PRIMARY KEY (id))";

    public StatusMigration( Connection connection) {
        try {
            connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
