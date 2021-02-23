package ru.job4j.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmailExam {
    private Map<String, User> container = new HashMap<>();

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

        public ArrayList<String> getEmails() {
            return emails;
        }
    }
}
