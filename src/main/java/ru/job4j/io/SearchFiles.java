package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends PrintFiles {
    private Predicate predicate;
    private List<Path> files = new ArrayList();

    SearchFiles(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            if (predicate.test(file)) {
                files.add(file.toAbsolutePath());
            }
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() {
        return files;
    }
}
