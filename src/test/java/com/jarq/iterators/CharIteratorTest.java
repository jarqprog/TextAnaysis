package com.jarq.iterators;

import com.jarq.model.FileContent;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CharIteratorTest {

    private FileContent fileContent;
    private Iterator<String> iterator;


    @Before
    public void setIterator() {
        try {
            fileContent = new FileContent("test1.txt");
            iterator = fileContent.charIterator();
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
        Iterator<String> it = new CharIterator(fileContent);
        Assert.assertNotNull(it);
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals("B", it.next());
    }

    @Test(expected = IOException.class)
    public void testConstructorUsingDirectoryInsteadOfFile() throws IOException {
        new CharIterator(new FileContent("fake_file"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testConstructorWithFileContentNotContainingText() throws IOException, NoSuchElementException {
        new CharIterator(new FileContent("numbers.txt"));
    }

    @Test
    public void testHasNext() throws IOException {

        Iterator<String> it = new CharIterator(new FileContent("test_two_words.txt"));
        Assert.assertTrue(it.hasNext());
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        Assert.assertTrue(it.hasNext());
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testNext() {
        iterator.next();
        assertEquals("a", iterator.next());
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()) {
            sb.append(iterator.next());
        }
        Assert.assertEquals("rdzodawnotemuwodleglejgalaktyceposzedlkotnadrzeczkeinic", sb.toString());
        Assert.assertEquals("", iterator.next());
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };

}