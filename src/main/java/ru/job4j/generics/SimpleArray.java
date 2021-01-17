package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {

    private T[] array;

    public void add(T model) {
        array[array.length] = model;
    }

    public void set(int index, T model) {
        array[index] = model;
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
        if (index > array.length) {
            return null;
        }
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(array).iterator();
    }
}
