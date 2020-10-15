package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class CountBarrier.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 15.10.2020
 */
@ThreadSafe
public class CountBarrier {
    @GuardedBy("monitor")
    private final Object monitor = this;
    private final int total;
    private int count;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            try {
                while (count < total) {
                    System.out.println(Thread.currentThread().getName() + " waiting");
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("The await method was started");
        }
    }
}
