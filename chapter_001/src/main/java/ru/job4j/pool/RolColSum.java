package ru.job4j.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Class RolColSum.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 01.11.2020
 */
public class RolColSum {

    /**
     * Sum sums [ ].
     *
     * @param matrix the matrix
     * @return the sums [ ]
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];

        for (int y = 0; y < matrix.length; y++) {
            int rowSum = 0;
            int colSum = 0;
            for (int x = 0; x < matrix.length; x++) {
                rowSum += matrix[y][x];
                colSum += matrix[x][y];
            }
            sums[y] = new Sums(rowSum, colSum);
        }
        return sums;
    }

    /**
     * Async sum sums [ ].
     *
     * @param matrix the matrix
     * @return the sums [ ]
     */
    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        List<CompletableFuture<Sums>> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            list.add(getTask(matrix, i));
        }
        getFutureResult(sums, list);
        return sums;
    }

    private static void getFutureResult(Sums[] sums, List<CompletableFuture<Sums>> list) {
        for (int i = 0; i < list.size(); i++) {
            try {
                sums[i] = list.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int rowSum = 0;
            int colSum = 0;

            for (int i = 0; i < matrix.length; i++) {
                rowSum += matrix[index][i];
                colSum += matrix[i][index];
            }
            return new Sums(rowSum, colSum);
        });
    }

    /**
     * The type Sums.
     */
    public static class Sums {
        private final int getRowSum;
        private final int colSum;

        /**
         * Instantiates a new Sums.
         *
         * @param rowSum the row sum
         * @param colSum the col sum
         */
        public Sums(int rowSum, int colSum) {
            this.getRowSum = rowSum;
            this.colSum = colSum;
        }

        /**
         * Gets row sum.
         *
         * @return the row sum
         */
        public int getRowSum() {
            return getRowSum;
        }

        /**
         * Gets col sum.
         *
         * @return the col sum
         */
        public int getColSum() {
            return colSum;
        }
    }
}
