package ru.job4j.collection;

import java.util.Iterator;

import ru.job4j.list.SimpleArrayList;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        if (set.size() == 0) {
            set = new SimpleArrayList<>(1);
        }
        if (contains(value)) return false;
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            rsl = t == value || rsl;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}