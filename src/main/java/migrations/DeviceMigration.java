package migrations;

import java.sql.Connection;
import java.sql.SQLException;


public class DeviceMigration {
    String sql = "CREATE TABLE devices ( id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, importDate DATE NOT NULL, quantity INT NOT NULL, id_category INT NOT NULL, id_status INT NOT NULL, PRIMARY KEY (id), FOREIGN KEY (id_category) REFERENCES categories(id), FOREIGN KEY (id_status) REFERENCES status(id))";

    public DeviceMigration(Connection connection) {
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
