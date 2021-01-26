package ru.job4j.collection;

import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private int modCount = 0;
    private Node<E> head;
    private int size = 0;

    public void add(E value) {
        modCount++;
        size++;
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
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNextNode();
        }
        return node.getItem();
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

