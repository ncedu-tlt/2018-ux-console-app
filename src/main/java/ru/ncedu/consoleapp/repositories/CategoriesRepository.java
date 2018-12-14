package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository implements Repository<Category> {

    private static CategoriesRepository instance;

    private CategoriesRepository() {
    }

    public static CategoriesRepository getInstance() {
        if (instance == null) {
            instance = new CategoriesRepository();
        }
        return instance;
    }

    public List<Category> get() {
        List<Category> categories = new ArrayList<>();

        Connection connection = DBUtils.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT category_id, category_name," +
                    " category_description FROM category");
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getLong("category_id"));
                category.setName(resultSet.getString("category_name"));
                category.setDescription(resultSet.getString("category_description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return categories;
    }

    @Override
    public Category get(long id) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM category WHERE category_id=(?)");
            statement.setLong(1, id);
            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getLong("category_id"));
                category.setName(resultSet.getString("category_name"));
                category.setDescription(resultSet.getString("category_description"));

                return category;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
    }

    public Category add(Category object) {
        Category category = new Category(object);

        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO category (category_name, category_description) " +
                            "VALUES (?, ?) RETURNING category_id");
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.execute();

            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                category.setId(resultSet.getLong("category_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return category;
    }

    public Category update(Category object) {
        Category category = new Category(object);

        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "UPDATE category SET (category_name, category_description) = (?, ?) " +
                            "WHERE category_id=(?)");
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setLong(3, category.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return category;
    }

    public boolean remove(Category category) {
        return remove(category.getId());
    }

    @Override
    public boolean remove(long id) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE FROM category WHERE category_id=(?)");
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return true;
    }
}
