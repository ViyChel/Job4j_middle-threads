package ru.job4j.pool;

/**
 * Class Task.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 25.10.2020
 */
public class Task implements Runnable {

    @Override
    public void run() {
        int summ = 0;
        for (int i = 1; i < 10; i++) {
            summ += i;
        }
        System.out.print(summ);
    }
}
