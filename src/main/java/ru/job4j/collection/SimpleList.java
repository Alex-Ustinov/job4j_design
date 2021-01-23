package ru.job4j.collection;

import java.lang.reflect.Array;
import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private Node<E>[] container;
    private int size = 0;
    private int modCount = 0;
    private Iterator<E> cursor = Collections.emptyIterator();
    private int flag = 0;
    transient Node<E> first;
    transient Node<E> last;

    SimpleList() {
        container = new <Node<E>>[10];
    }

    SimpleList(int size) {
        container = new <Node<E>>[size];
    }

    public Node<E>[] grows () {
        Node<E>[] newList = new <Node<E>>[size * 2];
        System.arraycopy(container, 0 , newList, 0, size * 2);
        return newList;
    }

    public void add(E value) {
        modCount++;
        Node<E> newNode = new Node<>(value, last, null);
        if (last == null) {
            first = new Node<>(value, newNode, null);
        } else {
            last.setNextNode(newNode);
        }
        last = newNode;
        if (size == container.length) {
            grows();
        }
        container[size++] = newNode;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return container[index].getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    class CustomIterator implements Iterator<E> {
        private int define = modCount;
        private int flag = 0;

        @Override
        public boolean hasNext() {
            return flag < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (define != modCount) {
                throw new ConcurrentModificationException();
            }
            return container[flag++].getItem();
        }
    }
}
