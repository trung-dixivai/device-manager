package migrations;


import java.sql.Connection;


public class CategoryMigration {
    String sql = "CREATE TABLE categories ( id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, isRent BOOLEAN NOT NULL, PRIMARY KEY (id))";

    public CategoryMigration(Connection connection) {
        try {
            connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
