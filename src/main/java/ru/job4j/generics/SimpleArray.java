package ru.job4j.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    public void set(int index, T model) {
        try {
            Objects.checkIndex(index, ids);
            array[index] = model;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(int index) {
        Class<?> arrayCompType = array.getClass().getComponentType();
        T result = (T) Array.newInstance(arrayCompType, array.length - 2);
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - 2);
    }

    public T get(int index) {
        try {
            Objects.checkIndex(index, ids);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(array).iterator();
    }
}
