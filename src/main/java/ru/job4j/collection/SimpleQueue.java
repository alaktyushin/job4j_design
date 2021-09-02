package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        size--;
        return in.pop();
    }

    public void push(T value) {
        size++;
        for (int i = 0; i < size - 1; i++) {
                out.push(in.pop());
        }
        out.push(value);
        for (int i = 0; i < size; i++) {
            in.push(out.pop());
        }
    }
}
