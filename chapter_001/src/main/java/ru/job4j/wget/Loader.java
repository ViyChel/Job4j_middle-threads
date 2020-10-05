package ru.job4j.wget;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class Loader.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 05.10.2020
 */
public class Loader {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws Exception {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Download file = new FileDownload(url, speed);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> path = executorService.submit(file);
        executorService.shutdown();
        System.out.println(path.get());

    }
}
