package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.postgresql.Driver");
//        String url = "jdbc:postgresql://http://127.0.0.1:5934//cars";
//        String login = "postgres";
//        String password = "password";
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader("app.properties.txt"))) {
            for (String line = in.readLine(); line != null; line = in.readLine() ) {
                String dataBD = 
            }
        }

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
