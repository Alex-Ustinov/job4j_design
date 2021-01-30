package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;
    private Iterator<User> cursor = Collections.emptyIterator();

    User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        System.out.println(5 % 43);
        System.out.println(5 % 16);
        HashMap<User, Object> users = new HashMap();
        users.put(new User("Alex", 0, new GregorianCalendar(2017, 0, 25)), new Object());
        users.put(new User("Alex", 0, new GregorianCalendar(2017, 0, 25)), new Object());
        System.out.println(users);
    }
}
