package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.function.Predicate;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        int index = 0;
        String[] initialArgs = new String[100];
        for (String arg : args) {
            String[] keyVal = arg.split("=");
            if (keyVal.length == 2) {
                initialArgs[index++] = keyVal[1];
            }
        }
        this.args = initialArgs;
        valid();
    }

    public String[] getArgs() {
        return args;
    }

    public boolean valid() {
        return this.args.length == 3 ? true : false;
    }

    public String directory() {
        if (valid()) {
            return args[0];
        }
        return "";
    }

    public String exclude() {
        if (valid()) {
            return args[1];
        }
        return "";
    }

    public String output() {
        if (valid()) {
            return args[2];
        }
        return "";
    }
}