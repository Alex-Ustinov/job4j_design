package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Alex";
        int age = 32;
        boolean isWork = true;
        double salary = 1.33;
        float location = (float) 7.836849;
        byte id = 97;
        long phoneNumber = 213123123;
        LOG.debug("User info name : {}, id: {}, age : {}", name, id, age);
        LOG.debug("isWork : {} , salary : {}, phoneNumber : {} location : {}", isWork, salary, phoneNumber, location);
    }
}