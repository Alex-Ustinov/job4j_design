package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            br.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String userFile = readFile(path);
        String[] userWords = userFile.split(" ");
        int nUser = (int) Math.floor(Math.random() * userWords.length);
        String userWord = userWords[nUser];

        String botFile = readFile(botAnswers);
        String[] botWords = botFile.split(" ");
        int nBot = (int) Math.floor(Math.random() * botWords.length);
        String botWord = botWords[nBot];

        writeDataInFile(path, userWord);

        if (userWord.equals(STOP)) {
            run();
        } else if (userWord.equals(OUT)) {
            return;
        } else if (userWord.equals(CONTINUE)) {
            writeDataInFile(path, botWord);
        } else {
            writeDataInFile(path, botWord);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./userFile.doc", "./botFile.doc");
        cc.run();
    }
}
