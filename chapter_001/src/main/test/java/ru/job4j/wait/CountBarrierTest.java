package ru.job4j.wait;

import org.junit.Test;

/**
 * Class CountBarrierTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 15.10.2020
 */
public class CountBarrierTest {

    @Test
    public void whenStartTwoThread() throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(5);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 0; i < 6; i++) {
                        countBarrier.count();
                    }

                },
                "First"
        );
        Thread second = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.await();
                },
                "Second"
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}