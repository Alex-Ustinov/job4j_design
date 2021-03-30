package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

public class SearcherFiles extends SimpleFileVisitor<Path> {
    private Predicate predicate;
    private List<Path> result;

    SearcherFiles(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            if (predicate.test(file)) {
                result.add(file.toAbsolutePath());
            }
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getResult() {
        return result;
    }
}
