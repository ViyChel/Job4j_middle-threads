package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class RolColSumTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 01.11.2020
 */
public class RolColSumTest {

    @Test
    public void whenSumMatrixThenReturnCorrectSum() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] result = RolColSum.sum(matrix);
        assertThat(result[0].getRowSum(), is(6));
        assertThat(result[0].getColSum(), is(12));
        assertThat(result[1].getRowSum(), is(15));
        assertThat(result[1].getColSum(), is(15));
        assertThat(result[2].getRowSum(), is(24));
        assertThat(result[2].getColSum(), is(18));
    }

    @Test
    public void whenAsyncSumMatrixThenReturnCorrectSum() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] result = RolColSum.asyncSum(matrix);
        assertThat(result[0].getRowSum(), is(6));
        assertThat(result[0].getColSum(), is(12));
        assertThat(result[1].getRowSum(), is(15));
        assertThat(result[1].getColSum(), is(15));
        assertThat(result[2].getRowSum(), is(24));
        assertThat(result[2].getColSum(), is(18));
    }
}