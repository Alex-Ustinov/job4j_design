package ru.job4j.collection;

import java.util.*;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> simpleArray = new SimpleArray();
    private int modCount = 0;

    SimpleSet() {
        simpleArray = new SimpleArray(10);
    }

    SimpleSet(int size) {
        simpleArray = new SimpleArray(size);
    }

    public void add(T model) {
        if (contains(model)) {
            simpleArray.add(model);
        }
    }

    public boolean contains(T value) {
        for (T item : simpleArray) {
            if (Objects.equals(item, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
