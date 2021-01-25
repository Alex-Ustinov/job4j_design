package ru.job4j.collection;

import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private int modCount = 0;
    private Node<E> head;

    public void add(E value) {
        modCount++;
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<E> last = head;
        while (last.getNextNode() != null) {
            last = last.getNextNode();
        }
        last.setNextNode(newNode);
    }

    public E get(int index) throws IndexOutOfBoundsException {
        int counter = 1;
        Node<E> node = head;
        E result = node.getItem();
        while (counter <= index) {
            if (node.getNextNode() != null) {
                result = node.getItem();
                node = node.getNextNode();
            } else {
                result = node.getItem();
            }
            counter++;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }
    class CustomIterator implements Iterator<E> {
        private int define = modCount;
        private Node<E> node = head;

        @Override
        public boolean hasNext() {
            return node.getNextNode() != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (define != modCount) {
                throw new ConcurrentModificationException();
            }
            E result = node.getItem();
            node = node.getNextNode();
            return result;
        }
    }
}

