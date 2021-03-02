package ru.job4j.collection;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Analizy {
    public void unavailable(String source, String target) throws FileNotFoundException {
        ArrayList<ArrayList<String>> innerLog = new ArrayList<>();
        String[] blockSignals = {"400", "500"};
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] logLine = line.split(" ");
                String serverSignal = logLine[0];
                String time = logLine[1];
                for (String signal : blockSignals) {
                    if (serverSignal.equals(signal)) {
                        ArrayList<String> newItem = new ArrayList<String>(2);
                        newItem.add(time);
                        innerLog.add(newItem);
                    }
                }
                if (innerLog.size() > 0 && innerLog.get(innerLog.size() - 1).size() == 1) {
                    if (logLine.length > 0 && !serverSignal.equals("400") && !serverSignal.equals("500")) {
                        ArrayList<String> lastItem = innerLog.get(innerLog.size() - 1);
                        lastItem.add(time);

                        //innerLog.remove(innerLog.size() - 1);
                        //innerLog.add(innerLog.size() - 1, lastItem);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            for (ArrayList<String> log : innerLog) {
                out.println(log.get(0) + ";" + log.get(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Analizy().unavailable("server.log.txt", "unavailable.csv");
    }
}
