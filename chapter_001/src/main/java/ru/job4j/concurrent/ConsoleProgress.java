package ru.job4j.concurrent;

/**
 * Class ConsoleProgress.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 05.10.2020
 */
public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] process = {"--", "\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[0]);
                Thread.sleep(500);
                System.out.print("\r load: " + process[1]);
                Thread.sleep(500);
                System.out.print("\r load: " + process[2]);
                Thread.sleep(500);
                System.out.print("\r load: " + process[3]);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }
}
