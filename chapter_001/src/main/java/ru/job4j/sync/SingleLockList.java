package ru.job4j.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Class SingleLockList.
 *
 * @param <T> the type parameter
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.10.2020
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArrayList<T> list = new SimpleArrayList<>();

    /**
     * Add.
     *
     * @param value the value
     */
    public synchronized void add(T value) {
        list.add(value);
    }

    /**
     * Get t.
     *
     * @param index the index
     * @return the t
     */
    public synchronized T get(int index) {
        return list.get(index);
    }


    @Override
    public synchronized Iterator<T> iterator() {
        return copy().iterator();
    }

    private synchronized SimpleArrayList<T> copy() {
        SimpleArrayList<T> result = new SimpleArrayList<>();
        list.iterator().forEachRemaining(result::add);
        return result;
    }
}
