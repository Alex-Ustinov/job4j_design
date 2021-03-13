package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    //private Set<FileProperty> files;
    private ArrayList<FileProperty> files = new ArrayList<FileProperty>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(attrs.size(), file.getFileName().toString());
        if (attrs.isRegularFile()) {
            if (files.size() > 0 && files.contains(currentFile)) {
                System.out.println(file.toAbsolutePath());
            } else {
                files.add(new FileProperty(attrs.size(), file.getFileName().toString()));
            }
        }
        //files.add(new FileProperty(attrs.size(), file.getFileName().toString()));
        //System.out.println(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}