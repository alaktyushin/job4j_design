package ru.job4j.collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import java.util.Iterator;

public class ForwardLinkedRevertTest {

    @Test
    public void whenAddThenIter() {
        ForwardLinkedRevert<Integer> linked = new ForwardLinkedRevert<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinkedRevert<Integer> linked = new ForwardLinkedRevert<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAdd3AndRevertThenIter() {
        ForwardLinkedRevert<Integer> linked = new ForwardLinkedRevert<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        ForwardLinkedRevert<Integer> emptyList = new ForwardLinkedRevert<>();
        assertFalse(emptyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        ForwardLinkedRevert<Integer> singleList = new ForwardLinkedRevert<>();
        singleList.add(1);
        assertFalse(singleList.revert());
    }
}