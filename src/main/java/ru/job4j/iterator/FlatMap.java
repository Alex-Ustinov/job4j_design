package ru.job4j.iterator;

import java.util.*;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;

    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        Iterator<T> it = data.next();
        if (cursor.hasNext()) {
            return true;
        }
        System.out.println("cursor " + cursor.hasNext());
        System.out.println("it " + it.hasNext());
        while (!cursor.hasNext() && it.hasNext()) {
            //System.out.println(it);
            cursor = it;
        }
        return it.hasNext() && cursor.hasNext();
    }

    @Override
    public T next() {
        System.out.println("start");
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T t = cursor.next();
        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}