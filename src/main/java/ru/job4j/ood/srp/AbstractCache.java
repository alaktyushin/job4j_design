package ru.job4j.ood.srp;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference(value));
    }

    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    protected abstract V load(K key);

    protected abstract void print(K key);
}

/*
 * Нарушение принципа SRP в методе print() -
 * необходимо вынести вывод содержимого кэша по ключу за рамки данного класса.
 * Метод print() решает отдельную задачу и находится в ответственности процесса,
 * регламентирующего назначение и формат вывода.
 * */