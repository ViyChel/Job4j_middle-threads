package ru.job4j.nonblock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class CacheTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 23.10.2020
 */
public class CacheTest {

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Cache cache = new Cache();
        cache.add(new Base(1, "one"));
        Thread thread1 = new Thread(
                () -> {
                    try {
                       cache.update(new Base(1, "new value"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, "other value"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(ex.get().getMessage(), is("Versions do not match"));
    }

}