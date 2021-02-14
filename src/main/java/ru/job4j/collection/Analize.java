package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Set<User> checkSet = current.stream().
                collect(Collectors.toSet());
        Map<Integer, User> difference = previous.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        for (User user : current) {
            if (difference.keySet().contains(user.id)) {
                if (!user.name.equals(difference.get(user.id).getName())) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }

        for (User previousUser : previous) {
            if (checkSet.add(previousUser)) {
                info.deleted++;
            }
        }

//        HashSet<User> differenceHashSet = new HashSet<User>(difference.values());
//        Iterator<User> iterator = differenceHashSet.iterator();
//        while (iterator.hasNext()) {
//            User checkUser = iterator.next();
//            if (!current.contains(checkUser)) {
//                info.deleted++;
//            }
//        }

        return info;
    }

    public static class User {
        private int id;
        private String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }


        @Override
        public String toString() {
            return "User{"
                    + "id=" + id
                    + ", name='" + name + '\''
                    + '}';
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
            return Objects.hash(id);//name
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
        User user8 = new User(6, "Sam");

        List<User> previous = List.of(user1, user2, user3, user4, user5);
        List<User> current = List.of(user2, user6, user7, user8);
        Analize analize = new Analize();
        Info info = analize.diff(previous, current);
        System.out.println(info);
    }
}
