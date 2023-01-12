package daos;

import models.Category;
import models.Device;
import models.Notification;
import models.Status;
import utils.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CategoryDao implements Dao<Category> {
    private Connection connection = ConnectDatabase.connection;

    @Override
    public Notification<Category> create(Category data) {
        Notification<Category> notification = new Notification<>();
        if (connection != null) {
            try {
                String sql = "INSERT INTO categories ( name) VALUES ( ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, data.getName());
                preparedStatement.executeUpdate();
                notification.setCheck(true);
                notification.setData(data);
            } catch (SQLException e) {
                e.printStackTrace();
                notification.setCheck(false);
                notification.setString(e.getMessage());
            }

        }

        return notification;
    }

    @Override
    public Category findOne(int id) {
        String sql = "SELECT * FROM  categories WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Category(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("isRent"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Vector<Category> findAll() {
        Vector<Category> categories = new Vector<>();
        String sql = "SElect * From categories";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Boolean isRent = resultSet.getBoolean("isRent");
                System.out.println(isRent);
                categories.add(new Category(id,name,isRent));


            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Notification<Category> delete(int id) {
        Notification<Category> notification = new Notification<>();
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findOne(id);
        if (category == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            String sql = "DELETE from categories where id ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Xoa Thanh Cong");
            notification.setData(category);
            return notification;


        } catch (SQLException e) {
            notification.setCheck(false);
            notification.setString("Cập nhật thất bại");
            return notification;
        }

    }

    @Override
    public Notification<Category> update(int id, Category data) {
        Notification<Category> notification = new Notification<>();
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findOne(id);
        if (category == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Update categories set name=?,isRent=? ,WHERE id ? ");

            preparedStatement.setString(1, data.getName());
//            preparedStatement.setString(2,data.getRent());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Cập nhật thành công");

            notification.setData(category);
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CategoryDao categoryDao=new CategoryDao();
     Notification<Category> notification=categoryDao.create(new Category(1,"Thiết Bị Cố Định",true));

    }
}
