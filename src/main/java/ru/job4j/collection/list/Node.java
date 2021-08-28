package ru.job4j.collection.list;

public class Node<E> {

    E item;
    Node<E> next;

    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }
}
