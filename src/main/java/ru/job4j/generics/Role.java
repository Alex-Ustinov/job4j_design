package ru.job4j.generics;

public class Role extends Base {
    private String id;

    Role(String id) {
        super(id);
    }

    public String getId() {
        return id;
    }
}