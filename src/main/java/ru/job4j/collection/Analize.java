package ru.job4j.collection;

import java.util.*;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map difference = new HashMap<String, User>();
        for (User user : current) {
            if (!previous.contains(user)) {
                difference.put(user.id, user);
                //info.added++;
                info.deleted++;
            }
        }
        for (User previousUser : previous) {
            if (difference.keySet().contains(previousUser.id)) {
                info.changed++;
            }
        }
        return null;
    }

    public static class User {
        private int id;
        private String name;
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;
    }

    public static void main(String[] args) {

    }
}
