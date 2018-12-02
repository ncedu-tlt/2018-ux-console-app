package ru.ncedu.consoleapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.ncedu.consoleapp.consts.EnvironmentVariables.DB_URL;


public class DBUtils {

    public static Connection getConnection() {
        String connectionUrl = System.getenv(DB_URL);
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
