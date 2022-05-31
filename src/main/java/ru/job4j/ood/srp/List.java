package ru.job4j.ood.srp;

import java.util.Comparator;

public interface List<T> extends Iterable<T> {
    void add(T value);
    T set(int index, T newValue);
    T remove(int index);
    T get(int index);
    int size();

    void sort(Comparator comparator);
}
/*
 * Нарушение принципа SRP в методе sort() -
 * создание/модификация структуры списка и сортировка элементов списка
 * могут находится в разных зонах ответственности.
 * Целесообразно вынести метод sort() за рамки данного интерфейса.
 * */