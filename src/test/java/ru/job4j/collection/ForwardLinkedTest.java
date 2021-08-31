package ru.job4j.collection;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.addFirst(7);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenEmptyAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(7);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(7));
    }

    @Test
    public void whenNullAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(null);
        linked.add(1);
        linked.addFirst(7);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(7));
    }

    @Test
    public void whenDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(4);
        linked.add(3);
        linked.add(2);
        linked.delete();
        linked.delete();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenDeleteAll() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.delete();
        linked.delete();
        linked.delete();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmpty() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.delete();
    }
}