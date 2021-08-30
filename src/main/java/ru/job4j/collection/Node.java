package ru.job4j.collection;

public class Node<E> {

    E item;
    Node<E> next;

    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }
}
