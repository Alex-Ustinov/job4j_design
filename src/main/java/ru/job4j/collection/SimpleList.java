package ru.job4j.collection;

import java.lang.reflect.Array;
import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private Node<E>[] container;
    private int size = 0;
    private int modCount = 0;
    private Iterator<E> cursor = Collections.emptyIterator();
    private int flag = 0;

    SimpleList() {
        container = new <Node<E>>[10];
    }

    SimpleList(int size) {
        container = new <Node<E>>[size];
    }

    public Node<E>[] grows () {
        Node<E>[] newList = new <Node<E>>[size + 10];
        System.arraycopy(container, 0 , newList, 0, size + 10);
        return newList;
    }

    public void add(E value) {
        if () {

        }

    }

    public E get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return container[index].getItem();
    }

    @Override
    public Iterator<E> iterator() {
        new CustomIterator();
    }

    class CustomIterator implements Iterator<E> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
