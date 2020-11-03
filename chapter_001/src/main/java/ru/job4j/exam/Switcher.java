package ru.job4j.exam;

/**
 * Class Switcher.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 03.11.2020
 */
public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(
                () -> {
                    while (true) {
                        barrier.tryMaster();
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(1000);
                            barrier.doneSlave();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        barrier.trySlave();
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                            barrier.doneMaster();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
