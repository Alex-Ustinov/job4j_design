package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        //Files.walkFileTree(start, new PrintFiles());
        Predicate<Path> predicate = (path) -> path.endsWith("js");
        List<Path> t = search(start, predicate);
        for (Path a : t) {
            System.out.println(a);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition, root);
        //Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}