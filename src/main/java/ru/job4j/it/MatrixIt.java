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
        if (data.length - 1 >= column) {
            for (int i = column; i < data.length; i++) {
                if (data[i].length - 1 >= row) {
                    for (int y = row; y < data[i][y]; y++) {
                        if (Integer.valueOf(data[i][y]) != null) {
                            column = i;
                            row = y;
                            return true;
                        }
                    }
                } else {
                    row = 0;
                    column++;
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
        return data[column][row++];
    }
}