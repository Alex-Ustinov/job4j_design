package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                Integer number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    System.out.println(line + " is even");
                } else {
                    System.out.println(line + " not even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
