package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> userWordsList;
    private List<String> botWordsList;
    private Boolean flag = true;

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

        String userFile = readFile(path);
        String[] userWords = userFile.split("\\n");
        userWordsList = List.of(userWords);

        String botFile = readFile(botAnswers);
        String[] botWords = botFile.split("\\n");
        botWordsList = List.of(botWords);
    }

    public void run() {
        for (String w : userWordsList) {
            System.out.println(w);
        }
        for (String wo : botWordsList) {
            System.out.println(wo);
        }


        int nUser = (int) Math.floor(Math.random() * userWordsList.size());
        String userWord = userWordsList.get(nUser);

        int nBot = (int) Math.floor(Math.random() * botWordsList.size());
        String botWord = botWordsList.get(nBot);


        while (flag) {
            if (userWord.equals(STOP)) {
                flag = true;
            } else if (userWord.equals(OUT)) {
                flag = false;
            } else if (userWord.equals(CONTINUE)) {
                flag = true;
                writeDataInFile(path, userWord);
                writeDataInFile(path, botWord);
            } else {
                flag = true;
                writeDataInFile(path, userWord);
                writeDataInFile(path, botWord);
            }
            run();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("c:\\projects\\job4j_design\\userFile.docx", "c:\\projects\\job4j_design\\botFile.docx");
        cc.run();
    }
}
