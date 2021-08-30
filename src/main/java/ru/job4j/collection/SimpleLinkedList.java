package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        if (size == 0) {
            first = new Node<E>(value, last);
            last = first;
        } else {
        Node<E> previousNode = last;
        last = new Node<E>(value, null);
        previousNode.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    @Override
    public int size() {
        return this.size;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> currentNode;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentNode != last;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                currentNode = (currentNode == null) ? first : currentNode.next;
                return currentNode.item;
            }
        };
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.item = value;
            this.next = next;
        }
    }
}
