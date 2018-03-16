package com.jarq.iterators;

import com.jarq.model.FileContent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class WordIteratorTest {

    private FileContent fileContent;
    private Iterator<String> iterator;


    @Before
    public void setIterator() {
        try {
            fileContent = new FileContent("test1.txt");
            iterator = fileContent.wordIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroyObjects() {
        fileContent = null;
        iterator = null;
    }

    @Test
    public void testConstructorWithProperParameters() throws IOException {
        WordIterator it = new WordIterator(fileContent);
        Assert.assertNotNull(it);
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "Bardzo");
    }

    @Test
    public void testHasNext() throws IOException {

        FileContent fc = new FileContent("test_two_words.txt");
        Iterator<String> it = new WordIterator(fc);
        it.next();
        Assert.assertTrue(it.hasNext());
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testNext() throws IOException {

    }




}