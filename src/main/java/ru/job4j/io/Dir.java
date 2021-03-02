package ru.job4j.io;

import java.io.File;
import java.util.NoSuchElementException;

public class Dir {
    public void getDir() {
        File directory = new File("c:\\projects\\job4j_design");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException();
        }
        for (File subFile : directory.listFiles()) {
            System.out.println("file: " + subFile.getName() + " size: " + subFile.length());
        }
    }

    public static void main(String[] args) {
        new Dir().getDir();
    }
}
