package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    SimpleArray() {
        container = new Object[10];
    }

    SimpleArray(int size) {
        container = new Object[size];
    }

    public Object[] grow() {
        Object[] newContainer = new Object[size * 2];
        System.arraycopy(container, 0, newContainer, 0, size * 2);
        return newContainer;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    class InnerIterator implements Iterator<T> {
        private int define = modCount;
        private int flag = 0;

        @Override
        public boolean hasNext() {
            return flag < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (modCount != define) {
                throw new ConcurrentModificationException();
            }
            return (T) container[flag++];
        }
    }
}