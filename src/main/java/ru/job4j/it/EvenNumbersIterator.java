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
        if (data.length - 1 >= index || data.length == 0) {
            for (int i = index; i < data.length; i++) {
                if (data[i] % 2 == 0) {
                    index = i;
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
