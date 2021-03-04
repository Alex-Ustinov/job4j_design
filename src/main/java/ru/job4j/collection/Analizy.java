package ru.job4j.collection;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Analizy {
    public void unavailable(String source, String target) throws FileNotFoundException {
        ArrayList<ArrayList<String>> innerLog = new ArrayList<>();
        String[] blockSignals = {"400", "500"};
        boolean isActive = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source)); PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] logLine = line.split(" ");
                String serverSignal = logLine[0];
                String time = logLine[1];
                for (String signal : blockSignals) {
                    if (serverSignal.equals(signal) && isActive) {
                        out.println(serverSignal + " ; " + time);
                        isActive = false;
                    }
                }
                if (logLine.length > 0 && !serverSignal.equals("400") && !serverSignal.equals("500") && !isActive) {
                    out.println(serverSignal + " ; " + time);
                    isActive = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Analizy().unavailable("server.log.txt", "unavailable.csv");
    }
}
