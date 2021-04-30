package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, FileNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("jdbc:postgresql");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists " + tableName + "(%s, %s);",
                    "id serial primary key",
                    "name varchar(255)"
            );
            statement.execute(sql);
        }
        initConnection();
    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table " + tableName);
            statement.execute(sql);
        }
        initConnection();
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table "
                    + tableName + " add "
                    + columnName + " " + type
            );
            statement.execute(sql);
        }
        initConnection();
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table "
                    + tableName + " drop "
                    + columnName
            );
            statement.execute(sql);
        }
        initConnection();
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table "
                    + tableName + " drop "
                    + columnName + " to "
                    + newColumnName
            );
            statement.execute(sql);
        }
        initConnection();
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}