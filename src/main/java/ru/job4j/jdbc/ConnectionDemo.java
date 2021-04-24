package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.postgresql.Driver");
//        String url = "jdbc:postgresql://http://127.0.0.1:5934//cars";
//        String login = "postgres";
//        String password = "password";

        Config config = new Config("app.properties");
        config.load();
        String url = config.value("jdbc:postgresql");
        String login = config.value("login");
        String password = config.value("password");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
