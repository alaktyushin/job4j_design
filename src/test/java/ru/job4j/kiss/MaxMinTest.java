package ru.job4j.kiss;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void maxWhenSizeIsNull() {
        MaxMin maxMin = new MaxMin();
        ArrayList<Integer> list = new ArrayList<>();
        assertNull(maxMin.max(list, Integer::compareTo));
    }

    @Test
    public void minWhenSizeIsNull() {
        MaxMin maxMin = new MaxMin();
        ArrayList<Integer> list = new ArrayList<>();
        assertNull(maxMin.min(list, Integer::compareTo));
    }

    @Test
    public void maxIntegerWhenSizeIsGreaterThenNull() {
        MaxMin maxMin = new MaxMin();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(-2);
        assertThat(maxMin.max(list, Integer::compareTo), is (4));
    }

    @Test
    public void maxStringWhenSizeIsGreaterThenNull() {
        MaxMin maxMin = new MaxMin();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("333");
        list.add("a2");
        assertThat(maxMin.max(list, String::compareTo), is ("a2"));
    }

    @Test
    public void minWhenSizeIsGreaterThenNull() {
        MaxMin maxMin = new MaxMin();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(-2);
        assertThat(maxMin.min(list, Integer::compareTo), is (-2));
    }

    @Test
    public void minStringWhenSizeIsGreaterThenNull() {
        MaxMin maxMin = new MaxMin();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("333");
        list.add("a2");
        assertThat(maxMin.min(list, String::compareTo), is ("1"));
    }
}