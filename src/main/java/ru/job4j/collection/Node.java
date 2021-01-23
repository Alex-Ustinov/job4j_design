package ru.job4j.collection;

public class Node<E> {
    private E item;
    private Node<E> nextNode;
    private Node<E> previousNode;

    public E getItem() {
        return item;
    }
    Node(E item, Node<E> previousNode, Node<E> nextNode,) {
        this.item = item;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }
}
