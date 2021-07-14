package ru.job4j.collection;

import java.util.*;

public class EmailExam {
    private Map<User, User> container = new HashMap<>();

    public List<String> searchExistUser(User user) {
        List<String> existUser = new ArrayList<String>();
        for (Map.Entry<User, User> userInContainer : container.entrySet()) {
            for (String email : userInContainer.getValue().emails) {
                User grabUser = userInContainer.getKey();
                for (String targetUserEmail : user.emails) {
                    if (email.equals(targetUserEmail)) {
                        existUser.add(email);
                        removeExistUser(grabUser);
                    }
                }
            }
        }
        return existUser;
    }


    public void removeExistUser(User user) {
        container.remove(user);
    }

    public void changeUser(User user, List<String> existUserEmails) {
        for (String email : existUserEmails) {
            user.addEmail(email);
        }
    }

    public void addUser(User user) {
        List<String> existUser = searchExistUser(user);
        if (existUser.size() > 0) {
            changeUser(user, existUser);
            return;
        }
        container.put(user, user);
    }

    public Map<User, User> getContainer() {
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
