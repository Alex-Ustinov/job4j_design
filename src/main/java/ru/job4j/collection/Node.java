package ru.job4j.collection;

public class Node<E> {
    private E item;
    private Node<E> nextNode;

    public E getItem() {
        return item;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    Node(E item, Node<E> nextNode) {
        this.item = item;
        this.nextNode = nextNode;
    }
}
