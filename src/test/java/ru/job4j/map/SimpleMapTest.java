package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putAllDifferentThenExpand() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(2, 200);
        map.put(3, 300);
        map.put(4, 400);
        map.put(5, 500);
        map.put(6, 600);
        //map.put(7, 700);
        //map.put(8, 800);
        //map.put(9, 900);
        //map.put(10, 500);
        //map.put(11, 600);
        //map.put(5, 50000);
    }

    @Test
    public void putDoubledKey() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(2, 200);
        map.put(2, 300);
        assertThat(map.get(2), is (300));
    }

    @Test
    public void put2DoubledKeys() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(0, 500);
        map.put(2, 200);
        map.put(2, 300);
        assertThat(map.get(2), is (300));
        assertThat(map.get(0), is (500));
    }

    @Test
    public void get2times() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(0, 500);
        map.put(2, 200);
        map.put(2, 300);
        assertThat(map.get(2), is (300));
        assertThat(map.get(2), is (300));
    }

    @Test
    public void getIfEmpty() {
        SimpleMap map = new SimpleMap();
        assertNull(map.get(0));
    }

    @Test
    public void remove() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.remove(0);
        assertNull(map.get(0));
    }

    @Test
    public void removeThenPut() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.remove(0);
        map.put(0, 5);
        assertThat(map.get(0), is (5));
        assertThat(map.get(1), is (100));
    }

    @Test
    public void removeIfEmpty() {
        SimpleMap map = new SimpleMap();
        map.remove(0);
        assertNull(map.get(0));
    }

    @Test
    public void iterator() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(2, 200);
        map.put(3, 300);
        map.put(4, 400);
        map.put(5, 500);
        map.put(5, 50000);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNoSuchElement() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(2, 200);
        map.put(3, 300);
        map.put(4, 400);
        map.put(5, 500);
        map.put(5, 50000);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap map = new SimpleMap();
        map.put(0, 0);
        map.put(1, 100);
        map.put(2, 200);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        map.put(2, 2000);
        it.next();
    }
}