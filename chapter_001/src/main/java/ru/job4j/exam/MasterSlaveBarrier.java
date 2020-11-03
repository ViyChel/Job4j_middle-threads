package ru.job4j.exam;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class MasterSlaveBarrier.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 03.11.2020
 */
@ThreadSafe
public class MasterSlaveBarrier {
    @GuardedBy("monitor")
    private boolean isMaster = true;
    private final Object monitor = this;

    public void tryMaster() {
        synchronized (monitor) {
            while (!isMaster) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void trySlave() {
        synchronized (monitor) {
            while (isMaster) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void doneMaster() {
        synchronized (monitor) {
            this.isMaster = true;
            monitor.notifyAll();
        }

    }

    public void doneSlave() {
        synchronized (monitor) {
            this.isMaster = false;
            monitor.notifyAll();
        }
    }
}
