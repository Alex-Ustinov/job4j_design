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
        for (String us : existUser) {
            //System.out.println(us);
        }
        //System.out.println("---------");
        if (existUser.size() == 1) {
            //System.out.println("Get it");
            String keyInitialUser = existUser.iterator().next();
            User initialUser = container.get(keyInitialUser);
            for (String userEmails : user.emails) {
                initialUser.addEmail(userEmails);
            }
            System.out.println(initialUser);
            container.put(keyInitialUser, initialUser);
            return;
        }
        container.put(String.join(",", user.emails), user);
    }

    public Map<String, User> getContainer() {
        return container;
    }

    public User authorize(String email) {
        Predicate<String> predicate = (el) -> el.contains(email);
       List<String> existUser = container.keySet()
               .stream()
               .filter(predicate)
               .collect(Collectors.toList());
       if (existUser.size() == 2) {
           User initialUser = container.get(existUser.iterator().next());
           String keyJoinUser = existUser.iterator().next();
           User joinUser = container.get(keyJoinUser);
           for (String userEmails : joinUser.emails) {
               initialUser.addEmail(userEmails);
           }
           container.put(keyJoinUser, null);
           return initialUser;
       }
       return container.get(existUser.iterator().next());
    }

    class User {
        private ArrayList<String> emails = new ArrayList();

        User(ArrayList<String> emails) {
            this.emails = emails;
        }

        public boolean addEmail(String email) {
            //System.out.println("email  " + email);
            //System.out.println("emails  " + emails);
            if (!emails.contains(email)) {
                //System.out.println("!!!!!!!");
                emails.add(email);
                //System.out.println("emails  After" + emails);
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
