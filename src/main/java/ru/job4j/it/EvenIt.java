package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] numbers;
    private int point = 0;

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean hasEven = false;
        for (int i = point; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                hasEven = true;
                point = i;
                break;
            }
        }
        return hasEven;
    }

    @Override
    public Integer next() {
        if (numbers.length == 0 || numbers[point] % 2 != 0) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}
