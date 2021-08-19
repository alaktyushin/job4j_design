package ru.job4j.it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.NoSuchElementException;

public class EvenItTest {

    @Test
    public void when2evenAnd2notThenTrueFalse() {
        EvenIt it = new EvenIt(new int[] {4, 2, 1, 1});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenMultiCallhasNextThenTrue() {
        EvenIt it = new EvenIt(new int[] {1, 2, 3, 4});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void when2evenNextNotEven() {
        EvenIt it = new EvenIt(new int[] {4, 2, 1, 1});
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void whenReadSequence() {
        EvenIt it = new EvenIt(new int[] {1, 2, 3});
        it.hasNext();
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        EvenIt it = new EvenIt(new int[] {});
        it.next();
    }
}