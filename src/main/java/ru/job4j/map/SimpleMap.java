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
        int index = indexFor((int) key);
        if (index != -1) {
            //System.out.println("Collision with key " + key);
            return false;
        }
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        table[count++] = new MapEntry<>(key, value);
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return Objects.hash(hashCode) % capacity;
    }

    private int indexFor(int hashCode) {
        for (int i = 0; i < count; i++) {
            if (table[i] != null && hash((int) table[i].key) == hash(hashCode)) {
                return i;
            }
        }
        return -1;
    }

    private void expand() {
        MapEntry<K, V>[] tempTable = new MapEntry[count];
        for (int i = 0; i < tempTable.length; i++) {
            tempTable[i] = table[i];
        }
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
        int index = indexFor((int) key);
        return (index == -1) ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor((int) key);
        if (index == -1) {
            return false;
        }
        table[index] = null;
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