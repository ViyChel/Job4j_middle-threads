package ru.job4j.sync;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class ParseFileTest.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 09.10.2020
 */
public class ParseFileTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenContentWithoutUnicode() throws IOException {
        File file = folder.newFile("data.txt");
        Predicate<Integer> hex = x -> x < 0x80;
        ParseFile parseFile = new ParseFile(file);
        parseFile.saveContent("класс class");
        assertThat(parseFile.getContent(hex), is(" class"));
    }

    @Test
    public void whenContentWithUnicode() throws IOException {
        File file = folder.newFile("data.txt");
        Predicate<Integer> hex = x -> true;
        ParseFile parseFile = new ParseFile(file);
        parseFile.saveContent("класс class");
        assertThat(parseFile.getContent(hex), is("класс class"));
    }
}