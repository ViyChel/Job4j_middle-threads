package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ThreadPoolTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 25.10.2020
 */
public class ThreadPoolTest {
    PrintStream consoleStream = System.out;
    OutputStream outputStream = new ByteArrayOutputStream();
    PrintStream stream = new PrintStream(outputStream);

    @Test
    public void work() throws InterruptedException, IOException {
        System.setOut(stream);
        final ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.work(new Task());
        }
        Thread.sleep(2000);
        String expected = outputStream.toString();
        System.setOut(consoleStream);
        assertThat(expected, is("45454545454545454545"));
    }
}