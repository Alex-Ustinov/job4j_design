package ru.job4j.generics;

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
       //T[] newArray = new T[array.length - 1];
       //array = System.arraycopy(array, index, newArray, 0, array.length - 1);
        if (index <= array.length - 1) {
            for (int i = index; i < array.length; i++) {
               T shiftElement = array[i + 1];
               array[i] = shiftElement;
            }
            array[array.length - 1] = null;
        }
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
