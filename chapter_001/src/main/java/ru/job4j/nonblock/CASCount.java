package ru.job4j.nonblock;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Class CASCount.
 *
 * @param <T> the type parameter
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 20.10.2020
 */
@ThreadSafe
public class CASCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    /**
     * Increment.
     */
    public void increment() {
        Integer val;
        do {
            val = count.get();
        } while (!count.compareAndSet(val, val + 1));
    }

    /**
     * Get int.
     *
     * @return the int
     */
    public int get() {
        return count.get();
    }
}
