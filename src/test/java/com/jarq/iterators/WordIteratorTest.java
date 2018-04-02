package com.jarq.iterators;

import com.jarq.enums.Path;
import com.jarq.model.FileContent;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class WordIteratorTest {

    private FileContent fileContent;
    private Iterator<String> iterator;


    @Before
    public void setIterator() {
        try {
            fileContent = new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test1.txt");
            iterator = fileContent.wordIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        fileContent = null;
        iterator = null;
    }

    @Test
    public void testConstructorWithProperParameters() throws IOException {
        WordIterator it = new WordIterator(fileContent);
        assertNotNull(it);
        assertTrue(it.hasNext());
        assertEquals("Bardzo", it.next());
    }

    @Test(expected = IOException.class)
    public void testConstructorUsingDirectoryInsteadOfFile() throws IOException {
        new WordIterator(new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "fake_file"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testConstructorWithFileContentNotContainingText()
            throws IOException, NoSuchElementException {
        new CharIterator(new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "numbers.txt"));
    }

    @Test
    public void testHasNext() throws IOException {

        FileContent fc = new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt");
        Iterator<String> it = new WordIterator(fc);
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals("Bardzo", iterator.next());
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()) {
            sb.append(iterator.next());
        }
        assertEquals("dawnotemuwodleglejgalaktyceposzedlkotnadrzeczkeinic", sb.toString());
        assertEquals("", iterator.next());
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}
