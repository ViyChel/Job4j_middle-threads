package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SearchIndexTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 31.10.2020
 */
public class SearchIndexTest {

    @Test
    public void whenSearchNumber() {
        Integer[] numbers = {25, 1, 58, 47, 23, 87, 41, 2, 16, 98, 32, 152, 45};
        SearchIndex<Integer> searchIndex  = new SearchIndex<>(numbers, 58);
        assertThat(searchIndex.indexOf(), is(2));
    }

    @Test
    public void whenSearchWord() {
        String[] numbers = {"three", "one", "two"};
        SearchIndex<String> searchIndex  = new SearchIndex<>(numbers, "one");
        assertThat(searchIndex.indexOf(), is(1));
    }

    @Test
    public void whenIndexNotFound() {
        String[] numbers = {"three", "one", "two"};
        SearchIndex<String> searchIndex  = new SearchIndex<>(numbers, "five");
        assertThat(searchIndex.indexOf(), is(-1));
    }
}