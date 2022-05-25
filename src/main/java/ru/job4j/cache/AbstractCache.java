package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference(value));
    }

    public V get(K key) {
        SoftReference<V> v = cache.getOrDefault(key, null);
        V value;
        if (v != null) {
            value = v.get();
            System.out.println("value already in cache:");
        } else {
            value = load(key);
            put(key, value);
            System.out.println("value first loaded from disk:");
        }
        System.out.println(value);
        System.out.println();
        return value;
    }

    protected abstract V load(K key);
}