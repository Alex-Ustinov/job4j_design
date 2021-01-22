package ru.job4j.collection;

import java.util.Collections;
import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;
    private Iterator<T> cursor = Collections.emptyIterator();

    public void grow() {

    }

    public T get(int index) {
        return null;
    }

    public void add(T model) {
        if (size == container.length) {
            cursor = grow();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    class InnerIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }
}