package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        for (int i = 0; i < size; i++) {
            T node = in.pop();
            out.push(node);
            in.push(node);
        }
        T rsl = out.pop();
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
