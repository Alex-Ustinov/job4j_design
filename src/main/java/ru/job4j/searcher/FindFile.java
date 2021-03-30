package ru.job4j.searcher;

import ru.job4j.io.ArgZip;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FindFile {

    public List<File> getFilesList(Path path, String typeSearch, String fileName) throws IOException {
        Predicate<Path> predicate;
        if (typeSearch.equals("name")) {
            predicate = p -> p.toFile().getName().endsWith(fileName);
        } else {
            //Pattern pattern = Pattern.compile(fileName);
            //boolean flag = fileName.matches(typeSearch);
            predicate = p -> p.toFile().getName().matches(fileName);
        }

        List<Path> listFiles = Searcher.getListFiles(path, predicate);
        List<File> arrFiles = new ArrayList<File>();
        for (Path pathFile : listFiles) {
            File file = Paths.get(pathFile.toString()).toFile();
            arrFiles.add(file);
        }
        return arrFiles;
    }

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        AdjustArguments adjustArguments = new AdjustArguments(args);
        FindFile findFile = new FindFile();
        //FindFile findFile = new FindFile(new String[] {"-d=c:\\project\\job4j\\", "-t=name", "-n=project.txt",  "-o=result.txt"});
        Path path = Paths.get(adjustArguments.getDirectory());
        List<File> arrFiles = findFile.getFilesList(path, adjustArguments.getTypeSearch(), String adjustArguments.getFileName());
        File targetFile = new File(adjustArguments.getFileResult());
        findFile.packFiles(arrFiles, targetFile);
    }
}
