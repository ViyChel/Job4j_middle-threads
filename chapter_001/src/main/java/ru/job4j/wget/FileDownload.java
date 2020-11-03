package ru.job4j.wget;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

/**
 * Class FileDownload.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 05.10.2020
 */
public class FileDownload implements Download {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownload.class);
    private final int kbyte = 1024;
    private final String url;
    private final int speedKbyte;
    private final long period = 1000;

    /**
     * Instantiates a new Wget.
     *
     * @param url   the url
     * @param speed the speed
     */
    public FileDownload(String url, int speed) {
        this.url = url;
        this.speedKbyte = speed * kbyte;
    }

    @Override
    public String call() throws Exception {
        File target = createFile(url);
        String result = "File downloaded. Link: " + target.getAbsolutePath();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(target)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int threshold = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                threshold += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (threshold > speedKbyte) {
                    long timePassed = System.currentTimeMillis() - start;
                    if (period - timePassed > 0) {
                        Thread.sleep(sleepTime(timePassed));
                    }
                    start = System.currentTimeMillis();
                    threshold = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private File createFile(String url) {
        return new File(url.substring(url.lastIndexOf("/") + 1));
    }

    private long sleepTime(long time) {
        return this.period - time;
    }

}
