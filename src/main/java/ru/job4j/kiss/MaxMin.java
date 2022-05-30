package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
            return compareList(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compareList(value, comparator.reversed());
    }

    public <T> T compareList(List<T> value, Comparator<T> comparator) {
        T temp = null;
        if (value.size() > 0) {
            temp = value.get(0);
            for (int i = 1; i < value.size(); i++) {
                if (comparator.compare(value.get(i), temp) > 0) {
                    temp = value.get(i);
                }
            }
        }
        return temp;
    }
}