package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class ThreadPool.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 24.10.2020
 */
public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(3);
    private final List<Thread> threads = new LinkedList<>();

    private void init() {
        for (int i = 0; i < this.size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            tasks.poll().run();
                        }
                    });
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Work.
     *
     * @param job the job
     */
    public void work(Runnable job) {
        if (threads.isEmpty()) {
            init();
        }
        this.tasks.offer(job);

    }

    /**
     * Shutdown.
     */
    public void shutdown() {
        this.threads.forEach(Thread::interrupt);
    }
}
