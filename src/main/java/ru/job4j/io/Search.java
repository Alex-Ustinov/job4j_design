package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new PrintFiles());
    }

    public static List<Path> search(Path root, String ext) {
        Path allItems = root.getFileName();
        TreeMap fileTree = new TreeMap();

        return null;
//        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
//        Files.walkFileTree(root, searcher);
//        return searcher.getPaths();
    }
}