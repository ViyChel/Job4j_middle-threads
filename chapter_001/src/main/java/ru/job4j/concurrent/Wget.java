package ru.job4j.concurrent;

/**
 * Class Wget.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 04.10.2020
 */
public class Wget {
    public static void main(String[] args) {
        final Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index < 100; index++) {
                            System.out.print("\rLoading : " + index + "%");
                            Thread.sleep(1000);
                        }
                        System.out.println("\nLoaded!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
