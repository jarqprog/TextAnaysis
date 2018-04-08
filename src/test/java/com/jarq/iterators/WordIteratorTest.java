package com.jarq.iterators;

import com.jarq.enums.Path;
import com.jarq.model.FileContent;
import org.junit.*;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class WordIteratorTest {

    private Iterator<String> iterator;


    @Before
    public void setIterator() {
        String filename = Path.RESOURCES_DIRECTORY.getPath() + "test1.txt";
        try {
            FileContent fileContent = new FileContent(filename);
            iterator = fileContent.wordIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorWithProperParameters() {

        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
        assertEquals("Bardzo", iterator.next());
    }

    @Test
    public void testHasNext() throws IOException {
        String filename = Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt";

        FileContent fc = new FileContent(filename);
        Iterator<String> it = new WordIterator(fc);

        it.next();
        assertTrue(it.hasNext());

        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testNext() {

        StringBuilder sb = new StringBuilder();

        assertEquals("Bardzo", iterator.next());

        while(iterator.hasNext()) {
            sb.append(iterator.next());
        }

        assertEquals("dawnotemuwodleglejgalaktyceposzedlkotnadrzeczkeinic", sb.toString());
        assertEquals("", iterator.next());
    }
}
