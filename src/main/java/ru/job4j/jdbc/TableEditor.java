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

    public void createRequest(String sql) throws Exception {
        TableEditor tableEditor = new TableEditor(properties);
        try (Statement statement = tableEditor.connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private void initConnection() throws ClassNotFoundException, FileNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("jdbc:postgresql");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = "create table if not exists " + tableName;
        createRequest(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = "drop table " + tableName;
        createRequest(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = "alter table "
                    + tableName + " add "
                    + columnName + " " + type;
        createRequest(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = "alter table "
                    + tableName + " drop "
                    + columnName;
        createRequest(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = "alter table "
                    + tableName + " rename "
                    + columnName + " to "
                    + newColumnName;
        createRequest(sql);
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