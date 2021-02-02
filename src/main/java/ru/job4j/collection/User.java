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

      @Override
      public int hashCode() {
         return Objects.hash(name, children, birthday, cursor);
      }

//    @Override
//    public boolean equals(Object o) {
//        System.out.println("!!!!!!!!!!");
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday) && Objects.equals(cursor, user.cursor);
//    }

    public static void main(String[] args) {
        HashMap<User, Object> users = new HashMap();
        User user1 = new User("Alex", 0, new GregorianCalendar(2017, 0, 25));
        User user2 = new User("Alex", 0, new GregorianCalendar(2017, 0, 25));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(Objects.equals(user1, user2));
        System.out.println(users);
    }
}
