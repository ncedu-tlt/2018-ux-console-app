package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsRepository implements Repository<Product> {

    private static ProductsRepository instance;

    private final List<Product> products;

    private ProductsRepository() {
        products = new ArrayList<>();
    }

    public static ProductsRepository getInstance() {
        if (instance == null) {
            instance = new ProductsRepository();
        }
        return instance;
    }

    public List<Product> get() {
        List<Product> productsList = new ArrayList<>();

        Connection connection = DBUtils.getConnection();
        Statement  statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product");

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setCategoryId(resultSet.getLong("category_id"));
                product.setDescription(resultSet.getString("product_description"));

                productsList.add(product);
            }
        } catch (SQLException e) {
            System.out.println();
            e.printStackTrace();
        }
        finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return productsList;
    }

    @Override
    public Product get(long id) {

        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM product where product_id = ?");
            statement.setLong(1, id);
            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setCategoryId(resultSet.getLong("category_id"));
                product.setDescription(resultSet.getString("product_description"));

                return product;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

    }

    public Product add(Product object) {

        Product product = new Product(object);

        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("INSERT INTO product " +
                    "( product_name, category_id, product_description)" +
                    " VALUES ( ?, ?, ?) RETURNING product_id");
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategoryId());
            statement.setString(3, product.getDescription());
            statement.execute();

            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                product.setId(resultSet.getLong("product_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return product;
    }


    public Product update(Product object) {

        Product product = new Product(object);

        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "UPDATE product " +
                            "SET product_name = ?, category_id = ?, product_description = ? " +
                            "WHERE product_id = ? ");

            statement.setString(1, object.getName());
            statement.setLong(2, object.getCategoryId());
            statement.setString(3, object.getDescription());
            statement.setLong(4, object.getId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return product;
    }

    public boolean remove(Product product) {
        return remove(product.getId());
    }

    @Override
    public boolean remove(long id) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "DELETE FROM product " +
                            "WHERE product_id = ? ");

            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return true;
    }

    public List<Product> getByCategoryId(long id) {
        List<Product> productsList = new ArrayList<>();

        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM product" +
                            "WHERE category_id = ?");
            statement.setLong(1,id);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setCategoryId(resultSet.getLong("category_id"));
                product.setDescription(resultSet.getString("product_description"));

                productsList.add(product);
            }

        } catch (SQLException e) {
           throw new RuntimeException(e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }

        return productsList;
    }


}
