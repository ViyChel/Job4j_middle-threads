package ru.job4j.sync;

import java.io.*;

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

    public synchronized String getContent() {
        String result = "";
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            result = getData(1, input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public synchronized String getContentWithoutUnicode() {
        String result = "";
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            return getData(0x80, input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

/*    private String getData(int hex, Reader input) throws IOException {
        StringBuilder result = new StringBuilder();
        int data;
        while ((data = input.read()) != -1) {
            if (hex != 0x80) {
                result.append((char) data);
            } else {
                if (data < hex) {
                    result.append((char) data);
                }
            }
        }
        return result.toString();
    }*/

    private String getData(int hex, BufferedReader input) throws IOException {
        StringBuilder result = new StringBuilder();
        if (hex != 0x80) {
            String data;
            while ((data = input.readLine()) != null) {
                result.append(data);
            }
        } else {
            int data;
            while ((data = input.read()) != -1) {
                if (data < 0x80) {
                    result.append((char) data);
                }
            }
        }
        return result.toString();
    }
}