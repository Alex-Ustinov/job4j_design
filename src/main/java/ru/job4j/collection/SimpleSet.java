package ru.job4j.collection;

import java.util.*;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> simpleArray = new SimpleArray();

    SimpleSet() {
        simpleArray = new SimpleArray(10);
    }

    SimpleSet(int size) {
        simpleArray = new SimpleArray(size);
    }

    public void add(T model) {
        if (!Arrays.asList(simpleArray).contains(model)) {
            simpleArray.add(model);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleSet.InnerIterator();
    }

    class InnerIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return simpleArray.iterator().hasNext();
        }

        @Override
        public T next() {
            return simpleArray.iterator().next();
        }
    }
}
