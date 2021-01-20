package ru.job4j.generics;

public class User extends Base {
    private String id;

    User(String id) {
        super(id);
    }

    public String getId() {
        return id;
    }
}
