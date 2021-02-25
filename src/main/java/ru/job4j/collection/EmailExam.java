package ru.job4j.collection;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmailExam {
    private Map<String, User> container = new HashMap<>();

    public void addUser(User user) {
        Predicate<String> predicate = (el) -> Arrays.stream(el.split(","))
                .anyMatch(userEmail -> user.emails.contains(userEmail));
        List<String> existUser = container.keySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
        if (existUser.size() == 1) {
            String keyInitialUser = existUser.iterator().next();
            User initialUser = container.get(keyInitialUser);
            for (String userEmails : user.emails) {
                initialUser.addEmail(userEmails);
            }
            container.remove(keyInitialUser);
            container.put(String.join(",", initialUser.emails), initialUser);
            return;
        }
        container.put(String.join(",", user.emails), user);
    }

    public Map<String, User> getContainer() {
        return container;
    }

    class User {
        private ArrayList<String> emails = new ArrayList();

        User(ArrayList<String> emails) {
            this.emails = emails;
        }

        public boolean addEmail(String email) {
            if (!emails.contains(email)) {
                emails.add(email);
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "User{"
                    + "emails=" + emails
                    + '}';
        }

        public ArrayList<String> getEmails() {
            return emails;
        }
    }
}
