package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.function.Predicate;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        Zip zip = new Zip();
        for (File sours : sources) {
            zip.packSingleFile(sours, target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
        //new String[] {"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"}
        ArgZip argZip = new ArgZip(args);
        Path path = Paths.get(argZip.directory());
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith(argZip.exclude());
        List<Path> listFiles = Search.search(path, predicate);
        List<File> arrFiles = new ArrayList<File>();
        File targetFile = new File(argZip.output());
        for (Path pathFile : listFiles) {
            File file = Paths.get(pathFile.toString()).toFile();
            arrFiles.add(file);
        }
        new Zip().packFiles(arrFiles, targetFile);
    }
}