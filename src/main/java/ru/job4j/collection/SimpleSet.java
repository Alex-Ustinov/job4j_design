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

    public boolean checkingEquals(T item1, T item2) {
        return Objects.equals(item1, item2);
    }

    public void add(T model) {
        boolean flag = false;
        modCount++;
        /*
        while (simpleArray.iterator().hasNext()) {
           if (model.equals(simpleArray.iterator().next())) {
               flag = true;
               break;
            }
        }
        */
        for (T item : simpleArray) {
            if (checkingEquals(model, item)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            simpleArray.add(model);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleSet.InnerIterator();
    }

    class InnerIterator implements Iterator<T> {
        private Integer size = simpleArray.getSize();
        private int position = 0;
        private Integer checkModCount = modCount;

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (checkModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return simpleArray.get(position++);
        }
    }
}
