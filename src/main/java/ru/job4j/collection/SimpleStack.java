package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int size = linked.getSize();

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public T pop() {
        T rsl = linked.deleteLast();
        size = linked.getSize();
        return rsl;
    }

    public void push(T value) {
        linked.add(value);
        size = linked.getSize();
    }
}