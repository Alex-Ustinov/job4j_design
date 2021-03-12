package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate predicate;
    private Path root;
    //private PrintFiles printFiles = new PrintFiles();

    SearchFiles(Predicate predicate, Path root) {
        this.predicate = predicate;
        this.root = root;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() throws IOException {
        List<Path> result = new ArrayList();

        //Path tree = visitFile(root);
        //Path way = root.toAbsolutePath();
        Path items = Files.walkFileTree(root, new PrintFiles());
        Path path = items.getFileName();
        System.out.println(path);
        if (predicate.test(path)) {
            System.out.println(path);
            result.add(path);
        }
        return result;
    }
}
