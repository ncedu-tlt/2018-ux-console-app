package ru.ncedu.consoleapp.utils;

import java.sql.*;

import static ru.ncedu.consoleapp.consts.Views.DB_URL_VAR;
import static ru.ncedu.consoleapp.consts.Views.PASSWORD;
import static ru.ncedu.consoleapp.consts.Views.USER;


public class DBUtils {
    public static Connection getConnection() {
        return getEnvConnection();
    }

    private static Connection getEnvConnection() {
        String connectionUrl = System.getenv(DB_URL_VAR);
        String user = System.getenv(USER);
        String password = System.getenv(PASSWORD);
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
