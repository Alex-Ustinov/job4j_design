package ru.job4j.searcher;

public class AdjustArguments {

    private String[] args;

    AdjustArguments(String[] args) throws Exception {
        int index = 0;
        String[] initialArgs = new String[100];
        for (String arg : args) {
            String[] keyVal = arg.split("=");
            if (keyVal.length == 2) {
                initialArgs[index++] = keyVal[1];
            }
        }
        this.args = initialArgs;
        if (!valid()) {
            throw new Exception("arguments is not appropriate");
        }
    }

    public boolean valid() {
        return this.args.length == 4 ? true : false;
    }

    public String getDirectory() {
        if (this.args.length > 0) {
            return this.args[0];
        }
        return "";
    }

    public String getFileName() {
        if (this.args.length > 1) {
            return this.args[1];
        }
        return "";
    }

    public String getFileResult() {
        if (this.args.length >= 2) {
            return this.args[2];
        }
        return "";
    }

    public String getTypeSearch() {
        if (this.args.length >= 3) {
            return this.args[3];
        }
        return "";
    }
}
