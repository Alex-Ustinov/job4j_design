package ru.job4j.searcher;

import ru.job4j.io.ArgZip;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindFile {

    public List<File> getFilesList(Path path, String typeSearch, String fileName) throws IOException {
        Predicate<Path> predicate;
        if (typeSearch.equals("name")) {
            predicate = p -> p.toFile().getName().endsWith(fileName);
        } else {
            String patternString = preparePattern(fileName);
            Pattern pattern = Pattern.compile(patternString);
            predicate = p -> p.toFile().getName().matches(pattern.toString());
        }

        List<Path> listFiles = Searcher.getListFiles(path, predicate);
        List<File> arrFiles = new ArrayList<File>();
        for (Path pathFile : listFiles) {
            File file = Paths.get(pathFile.toString()).toFile();
            arrFiles.add(file);
        }
        return arrFiles;
    }

    private static String preparePattern(String pattern) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '*') {
                str.append(".*");
            } else if (c == '.') {
                str.append(".\\");
            } else {
                str.append(c);
            }
        }
        return str.toString();
    }

    public List<String> readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of(builder.toString().split(System.lineSeparator()));
    }

    public void writeDataInFile(String path, List<File> data) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (File file : data) {
                List<String> dataFile = readFile(file.getPath());
                for (String answer : dataFile) {
                    br.write(answer + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, Exception {
        AdjustArguments adjustArguments = new AdjustArguments(args);
        FindFile findFile = new FindFile();
        //FindFile findFile = new FindFile(new String[] {"-d=c:\\project\\job4j\\", "-t=name", "-n=project.txt",  "-o=result.txt"});
        Path path = Paths.get(adjustArguments.getDirectory());
        List<File> arrFiles = findFile.getFilesList(path, adjustArguments.getTypeSearch(), adjustArguments.getFileName());
        findFile.writeDataInFile(adjustArguments.getFileResult(), arrFiles);
    }
}
