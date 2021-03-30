package ru.job4j.searcher;

public class AdjustArguments {

    private String directory;
    private String typeSearch;
    private String fileName;
    private String fileResult;

    AdjustArguments(String[] args) {
        for (String arg : args) {
            String[] keyVal = arg.split("=");
            if (keyVal.length == 2) {
                if (keyVal[0].equals("-d")) {
                    this.directory = keyVal[1];
                } else if (keyVal[0].equals("-t")) {
                    this.typeSearch = keyVal[1];
                } else if (keyVal[0].equals("-n")) {
                    this.fileName = keyVal[1];
                } else if (keyVal[0].equals("-o")) {
                    this.fileResult = keyVal[1];
                }
            }
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileResult() {
        return fileResult;
    }

    public String getTypeSearch() {
        return typeSearch;
    }
}
