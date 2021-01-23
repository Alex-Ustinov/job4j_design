package ru.job4j.collection;

public class Node<E> {
    private E item;
    private Node<E> nextNode;
    private Node<E> previousNode;

    public E getItem() {
        return item;
    }
    Node(E item, Node<E> nextNode, Node<E> previousNode) {
        this.item = item;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }
}
