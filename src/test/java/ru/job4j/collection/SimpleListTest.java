package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SimpleListTest {

    @Test
    public void whenPushThenPoll() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //for (Integer node : list) {
        //    System.out.println(node);
        //}
        assertThat(list.get(3), is(3));
    }
}