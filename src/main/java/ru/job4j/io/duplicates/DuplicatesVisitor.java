package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Set<FileProperty> files = new HashSet<FileProperty>();
    private ArrayList<FileProperty> dublicate = new ArrayList<FileProperty>();

    public ArrayList<FileProperty> getDublicate() {
        return dublicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            FileProperty currentFile = new FileProperty(attrs.size(), file.getFileName().toString());
            boolean resultAdding = files.add(currentFile);
            if (!resultAdding) {
                dublicate.add(currentFile);
            }
        }
        return super.visitFile(file, attrs);
    }
}