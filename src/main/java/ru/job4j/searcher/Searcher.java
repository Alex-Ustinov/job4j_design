package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    public static List<Path> getListFiles(Path rootStart, Predicate<Path> predicate) throws IOException {
        SearcherFiles searcher = new SearcherFiles(predicate);
        Files.walkFileTree(rootStart, searcher);
        return searcher.getResult();
    }
}
