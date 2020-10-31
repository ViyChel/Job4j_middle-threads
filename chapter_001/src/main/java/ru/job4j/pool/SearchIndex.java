package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Class SearchIndex.
 *
 * @param <T> the type parameter
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 31.10.2020
 */
public class SearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] numbers;
    private final T value;
    private final int start;
    private final int end;
    /**
     * The constant THRESHOLD.
     */
    public static final long THRESHOLD = 10;

    /**
     * Instantiates a new Search index.
     *
     * @param numbers the numbers
     * @param value   the value
     */
    public SearchIndex(T[] numbers, T value) {
        this(numbers, value, 0, numbers.length);
    }

    private SearchIndex(T[] numbers, T value, int start, int end) {
        this.numbers = numbers;
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public int indexOf() {
        ForkJoinTask<Integer> task = new SearchIndex<>(numbers, value);
        return new ForkJoinPool().invoke(task);
    }

    @Override
    protected Integer compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return serialSearch();
        }
        SearchIndex<T> firstTask = new SearchIndex<>(numbers, value, start, start + length / 2);
        SearchIndex<T> secondTask = new SearchIndex<>(numbers, value, start + length / 2, end);
        firstTask.fork();
        secondTask.fork();
        int secondTaskResult = secondTask.join();
        int firstTaskResult = firstTask.join();
        return secondTaskResult != -1 ? secondTaskResult : firstTaskResult;
    }

    private int serialSearch() {
        int result = -1;
        for (int i = start; i < end; i++) {
            if (this.numbers[i] == this.value) {
                result = i;
                break;
            }
        }
        return result;
    }
}
