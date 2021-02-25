package ru.job4j.collection;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EmailExamTest {
    @Test
    public void whenAdd5and2TheSame() {
        EmailExam emailExam = new EmailExam();
        ArrayList<String> user1Emails = new ArrayList<>();
        user1Emails.add("xxx@ya.ru");
        user1Emails.add("foo@gmail.com");
        user1Emails.add("lol@mail.ru");
        ArrayList<String> user2Emails = new ArrayList<>();
        user2Emails.add("foo@gmail.com");
        user2Emails.add("ups@pisem.net");
        ArrayList<String> user3Emails = new ArrayList<>();
        user3Emails.add("xyz@pisem.net");
        user3Emails.add("vasya@pupkin.com");
        ArrayList<String> user4Emails = new ArrayList<>();
        user4Emails.add("ups@pisem.net");
        user4Emails.add("aaa@bbb.ru");
        ArrayList<String> user5Emails = new ArrayList<>();
        user5Emails.add("xyz@pisem.net");
        //EmailExam.User user1 = new EmailExam.User(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        EmailExam.User user1 = emailExam.new User(user1Emails);
        EmailExam.User user2 = emailExam.new User(user2Emails);
        EmailExam.User user3 = emailExam.new User(user3Emails);
        EmailExam.User user4 = emailExam.new User(user4Emails);
        EmailExam.User user5 = emailExam.new User(user5Emails);
        emailExam.addUser(user1);
        emailExam.addUser(user2);
        emailExam.addUser(user3);
        emailExam.addUser(user4);
        emailExam.addUser(user5);
        Map<String, EmailExam.User> test = emailExam.getContainer();
        assertThat(test.size(), is(2));
    }
}