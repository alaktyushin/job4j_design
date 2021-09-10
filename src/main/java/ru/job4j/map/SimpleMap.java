package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        if (Objects.equals(table[index], null)) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return true;
    }

    private int hash(int hashCode) {
        return Math.abs(Objects.hash(hashCode) - 31);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        MapEntry<K, V>[] tempTable = table;
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        for (var pair : tempTable) {
            if (pair != null) {
                put(pair.key, pair.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return (!Objects.equals(table[index], null)) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (Objects.equals(table[index], null)) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}