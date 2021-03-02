package ru.job4j.collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File sours = folder.newFile("out.txt");
        File target = folder.newFile("in.txt");

        try (BufferedReader in = new BufferedReader(new FileReader(sours))) {
            //in.lines().forEach(rsl::append);
        }

        try (PrintWriter print = new PrintWriter(target)) {
            print.println();
        }
    }
}