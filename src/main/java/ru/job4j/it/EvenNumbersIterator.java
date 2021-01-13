package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    private final int[] data;
    private int index = 0;

    EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index > 0) {
            index++;
        }
        if (data.length < index || data.length == 0) {
            return false;
        } else if (checkFurther() == -1) {
            return false;
        }
        return true;
    }

    private int checkFurther() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                System.out.println("i " + i);
                index = i;
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        System.out.println("index " + index);
        return data[index];
    }
}
