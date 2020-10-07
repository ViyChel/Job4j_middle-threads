package ru.job4j.sync;

import java.io.*;
import java.util.function.Predicate;

/**
 * Class ParseFile.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 07.10.2020
 */
public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public synchronized String getContent(Predicate<Integer> hex) {
        String result = "";
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            result = getData(hex, input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter o = new BufferedWriter(new FileWriter(file))) {
            o.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getData(Predicate<Integer> predicate, Reader input) throws IOException {
        StringBuilder result = new StringBuilder();
        int data;
        while ((data = input.read()) != -1) {
            if (predicate.test(data)) {
                result.append((char) data);
            }
        }
        return result.toString();
    }
}