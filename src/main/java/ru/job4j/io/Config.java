package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws FileNotFoundException {
        HashMap<String, String> config = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] data = line.split("=");
                if (data.length == 2) {
                    String key = data[0];
                    String value = data[1];
                    value = value.replaceAll("\\s+","");
                    if (value.contains("//")) {
                        String regExp = ".*////.*"; //[A-z,a-z,0-9]
                        Matcher matcher = Pattern.compile(regExp).matcher(value);
                        System.out.println(value + " " + matcher.start());
                        value = value.substring(0, matcher.start());
                    }
                    value.replaceAll("\\s","");
                    config.put(key, value);
                }
            }
            for (Map.Entry<String, String> item : config.entrySet()) {
                System.out.println("key " + item.getKey() + " value " + item.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        throw new UnsupportedOperationException("Don't impl this method yet!");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config newConfig = new Config("config.txt");
        try {
            newConfig.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}