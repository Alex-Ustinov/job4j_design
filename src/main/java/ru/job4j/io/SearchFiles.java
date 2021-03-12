package ru.job4j.io;

import java.util.function.Predicate;

public class SearchFiles {
    private Predicate predicate;

    SearchFiles (Predicate predicate) {
        this.predicate = predicate;
    }

    public String getPaths() {
        return "";
    }
}
