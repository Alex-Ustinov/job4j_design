package ru.job4j.collection;

import java.util.*;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map difference = new HashMap<String, User>();
        for (User previousUser : previous) {
            if (!current.contains(previousUser)) {
                System.out.println(previousUser.name);
                info.deleted++;
            } else {
                difference.put(previousUser.id, previousUser.name);
            }
        }
        for (User currentUser : current) {
            if (difference.keySet().contains(currentUser.id)) {
                if (!currentUser.name.equals(difference.get(currentUser.id))) {
                    info.changed++;
                }
            }
            if (!previous.contains(currentUser)) {
                info.added++;
            }
        }
        return info;
    }

    public static class User {
        private int id;
        private String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id;// && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        @Override
        public String toString() {
            return "Info{"
                        + "added=" + added
                        + ", changed=" + changed
                        + ", deleted=" + deleted
                        + '}';
        }
    }

    public static void main(String[] args) {
        User user1 = new User(1, "Alex");
        User user2 = new User(2, "Sam");
        User user3 = new User(3, "Tom");
        User user4 = new User(4, "Kate");
        User user5 = new User(5, "Pole");

        User user6 = new User(3, "Adam");
        User user7 = new User(5, "Nicole");

        List<User> previous = List.of(user1, user2, user3, user4, user5);
        List<User> current = List.of(user1, user2, user6, user7);
        Analize analize = new Analize();
        Info info = analize.diff(previous, current);
        System.out.println(info);
    }
}
