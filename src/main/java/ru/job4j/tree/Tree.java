package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    public Optional<Node<E>> search(Predicate<Node<E>> predicate) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                return Optional.of(el);
            }
            data.addAll(el.children);
        }
        return Optional.empty();
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = item -> item.children.size() > 2;
        if(search(predicate).isPresent()) {
            return false;
        }
        return true;
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
        Predicate<Node<E>> predicate = item -> item.value.equals(value);
        Optional<Node<E>> result = search(predicate);
        if (search(predicate).isPresent()) {
            return result;
        }
        return Optional.empty();
    }

}