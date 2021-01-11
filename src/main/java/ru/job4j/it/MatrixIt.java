package ru.job4j.it;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.length < column) {
            return false;
        } else if (data[data.length - 1].length == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (data.length - 1 >= column) {
            if (data[column].length - 1 >= row) {
                return data[column][row++];
            } else {
                row = 0;
                column++;
                for (int i = column; i < data.length; i++) {
                    if (data[column].length > 0) {
                        return data[column][row++];
                    } else {
                        column++;
                    }
                }
            }
        }
        return null;
    }
}