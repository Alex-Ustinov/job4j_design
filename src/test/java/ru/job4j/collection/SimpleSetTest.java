package ru.job4j.collection;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Iterator;


public class SimpleSetTest {
    @Test
    public void simpleSetIterator() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(2);
        simpleSet.add(null);
        simpleSet.add(1);

        Iterator<Integer> iterator = simpleSet.iterator();
        iterator.next();
        Integer rsl = iterator.next();
        assertThat(rsl, is(2));
    }
}

