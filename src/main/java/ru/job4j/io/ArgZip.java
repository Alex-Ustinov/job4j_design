package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.function.Predicate;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        String[] bunchArgs = args[0].split(" ");
        int index = 0;
        String[] initialArgs = new String[100];
        for (String arg : bunchArgs) {
            args[index++] = arg;
        }
        this.args = initialArgs;
        valid();
    }

    public boolean valid() {
        for (String arg : args) {
            String[] keyVal = arg.split("=");
            if (keyVal.length < 2) {
                System.out.println("There are not enough params");
                return false;
            }
        }
        return true;
    }

    public String directory() {
        return null;
    }

    public String exclude() {
        return null;
    }

    public String output() {
        String Way = "./";
        String condition = "";
        for (String arg : args) {
            String[] keyVal = arg.split("=");
            if (keyVal[0] == "-d") {
                Way = keyVal[1];
            }
            if (keyVal[0] == "-e") {
                condition = keyVal[1];
            }
        }
        Path path = Paths.get(Way);
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith(condition);
        return null;
    }
}