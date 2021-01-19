package ru.job4j.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private int ids = 0;
    private T[] array;

    public void setIds(int ids) {
        this.ids = ids;
    }

    public void add(T model) {
        array[ids] = model;
        setIds(ids++);
    }

    public void set(int index, T model) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, ids);
        array[index] = model;
    }

    public T remove(int index) {
        Objects.checkIndex(index, ids);
        T value = (T) array[index];
        Class<?> arrayCompType = array.getClass().getComponentType();
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[array.length - 1] = null;
        ids--;
        return value;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, ids);
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    class CustomIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor <= ids;
        }

        @Override
        public T next() {
            return array[cursor++];
        }
    }
}
