package ru.job4j.collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File sours = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try {
            PrintWriter print = new PrintWriter(sours);
            print.println("200 10:35:01");
            print.println("400 10:46:02");
            print.println("200 10:57:03");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Analizy().unavailable(sours.getPath(), target.getPath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("400 10:46:02 200 10:57:03"));
    }
}