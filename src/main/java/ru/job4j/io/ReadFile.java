package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            //Consumer<String> func = (item) -> Arrays.stream(item.split(" ")).filter();
            //in.lines().forEach(func);

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] log = line.split(" ");
                if (log[log.length - 2].equals("404")) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
