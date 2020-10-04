package ru.job4j.concurrent;

/**
 * Class ThreadState.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 04.10.2020
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.printf("first thread: %s   second thread: %s%n", first.getState(), second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.printf("first thread: %s   second thread: %s%n", first.getState(), second.getState());
        }
        System.out.printf("first thread: %s   second thread: %s%n", first.getState(), second.getState());
        System.out.println(Thread.currentThread().getName() + " is finished");
    }
}
