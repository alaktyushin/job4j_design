package ru.job4j.collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushLastThenPop() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushLast(1);
        stack.pushLast(2);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushLastThenPopLast() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushLast(1);
        stack.pushLast(2);
        assertThat(stack.popLast(), is(2));
    }

    @Test
    public void whenPushLastPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushLast(1);
        stack.pop();
        stack.pushLast(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushLastPushLastThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushLast(1);
        stack.pushLast(2);
        stack.pop();
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushLastPollThenPushPopLast() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushLast(1);
        stack.pop();
        stack.pushLast(2);
        assertThat(stack.popLast(), is(2));
    }

    @Test
    public void whenPushLastPushLastThenPollPopLast() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushLast(1);
        stack.pushLast(2);
        stack.pop();
        assertThat(stack.popLast(), is(2));
    }
}