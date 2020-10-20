package ru.job4j.nonblock;

import org.junit.Test;
import ru.job4j.nonblock.CASCount;

import static org.junit.Assert.*;

/**
 * Class CASCountTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 20.10.2020
 */
public class CASCountTest {
    @Test
    public void increment() throws InterruptedException {
        CASCount<Integer> casCount = new CASCount<>();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        casCount.increment();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(20, casCount.get());
    }
}