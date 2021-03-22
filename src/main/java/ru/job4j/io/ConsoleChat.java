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

    private List<String> log = new ArrayList<>();

    public List<String> readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of(builder.toString().split(System.lineSeparator()));
    }

    public void writeDataInFile(String path, List<String> data) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String answer : data) {
                br.write(answer + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        userWordsList = readFile(path);
        botWordsList = readFile(botAnswers);
    }

    public void run() {
        int nUser = (int) Math.floor(Math.random() * userWordsList.size());
        String userWord = userWordsList.get(nUser);

        int nBot = (int) Math.floor(Math.random() * botWordsList.size());
        String botWord = botWordsList.get(nBot);

        while (flag) {
            if (userWord.equals(STOP)) {
                flag = true;
            } else if (userWord.equals(OUT)) {
                flag = false;
                writeDataInFile(path, log);
            } else if (userWord.equals(CONTINUE)) {
                flag = true;
                log.add(userWord);
                log.add(botWord);
            } else {
                flag = true;
                log.add(userWord);
                log.add(botWord);
            }
            run();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("c:\\projects\\job4j_design\\userFile.docx", "c:\\projects\\job4j_design\\botFile.docx");
        cc.run();
    }
}
