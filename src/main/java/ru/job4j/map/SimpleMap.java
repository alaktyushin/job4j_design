package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == capacity) {
            return false;
        }
        int index = indexFor((int) key);
        if (index != -1) {
            table[index].value = value;
        } else {
            table[count++] = new MapEntry<>(key, value);
        }
        modCount++;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        return true;
    }

    private int hash(int hashCode) {
        return (int) Math.pow(hashCode, 4);
    }

    private int indexFor(int hashCode) {
        for (int i = 0; i < count; i++) {
            if (hash((Integer) table[i].key) == hash(hashCode)) {
                return i;
            }
        }
        return -1;
    }

    private void expand() {
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        System.arraycopy(table, 0, tempTable, 0, table.length);
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        System.arraycopy(tempTable, 0, table, 0, tempTable.length);
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < count; i++) {
            if (table[i].key == key) {
                return table[i].value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        for (int i = 0; i < count; i++) {
            if (table[i].key == key) {
                table[i].value = null;
                modCount++;
                return true;
            }
        }
        return false;
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