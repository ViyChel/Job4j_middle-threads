package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleBlockingQueueTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 16.10.2020
 */
public class SimpleBlockingQueueTest {
    @Test
    public void whenTwoOfferAndOnePoll() throws InterruptedException {
        final SimpleBlockingQueue<Integer> simpleQueue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    simpleQueue.offer(15);
                    simpleQueue.offer(25);
                },
                "Producer"
        );
        Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    System.out.println(simpleQueue.poll());
                },
                "Consumer"
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        Integer expected = simpleQueue.poll();
        assertThat(expected, is(25));
    }
}