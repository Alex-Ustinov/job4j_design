package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles {
    private Predicate predicate;
    private Path root;
    //private PrintFiles printFiles = new PrintFiles();

    SearchFiles(Predicate predicate, Path root) {
        this.predicate = predicate;
        this.root = root;
    }

    public List<Path> getPaths() throws IOException {
        Path tree = Files.walkFileTree(root, new PrintFiles());
        //Path way = root.toAbsolutePath();
        System.out.println(tree);
        List<Path> result = new ArrayList();
        Path path = tree.getFileName();
        if (predicate.test(path)) {
            System.out.println(path);
            result.add(path);
        }
        return result;
    }
}
