package ru.job4j.it;

import org.hamcrest.core.Is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfEquals() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7));
        ListUtils.removeIf(input, f -> f == 2);
        assertThat(Arrays.asList(0, 1, 3, 4, 8, 7), Is.is(input));
    }

    @Test
    public void whenRemoveIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7, 18));
        ListUtils.removeIf(input, f -> f % 2 == 0);
        assertThat(Arrays.asList(1, 3, 7), Is.is(input));
    }

    @Test
    public void whenReplaceIfEquals() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7));
        ListUtils.replaceIf(input, f -> f == 2, 999);
        assertThat(Arrays.asList(0, 1, 999, 3, 999, 4, 8, 7), Is.is(input));
    }

    @Test
    public void whenReplaceIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7, 18));
        ListUtils.replaceIf(input, f -> f % 2 == 0, 0);
        assertThat(Arrays.asList(0, 1, 0, 3, 0, 0, 0, 7, 0), Is.is(input));
    }

    @Test
    public void whenRemoveAllIfEquals() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7));
        ListUtils.removeAll(input, Arrays.asList(0, 2, 8));
        assertThat(Arrays.asList(1, 3, 4, 7), Is.is(input));
    }

    @Test
    public void whenRemoveAllIfEmptyList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7));
        ListUtils.removeAll(input, List.of());
        assertThat(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7), Is.is(input));
    }

    @Test
    public void whenRemoveAllIfFoundAndNotFound() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4, 8, 7, 18));
        ListUtils.removeAll(input, Arrays.asList(2, 22, 222));
        assertThat(Arrays.asList(0, 1, 3, 4, 8, 7, 18), Is.is(input));
    }
}