package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;

    public void add(T value) {
        size++;
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public void revert() {
        ForwardLinked<T> pocket = new ForwardLinked<>();
        Node<T> curr = this.head;
        Node<T> prev = null;

        while (curr.next != null) {
            prev = head;
            curr = curr.next;
            pocket.add(prev.value);
        }
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.next == null) {

            T rsl = head.value;
            head = null;
            return rsl;
        }
        Node<T> last = head;
        Node<T> beforeLast = head;
        while (last.next != null) {
            beforeLast = last;
            last = head.next;
        }
        beforeLast.next = null;
        return last.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}