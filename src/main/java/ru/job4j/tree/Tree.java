package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean search(Predicate<Node<E>> predicate) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                return false;
            }
            data.addAll(el.children);
        }
        return true;
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = item -> item.children.size() > 2;
        return search(predicate);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent() && !findBy(parent).get().children.contains(child)) {
            findBy(parent).get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}