package com.jarq.iterators;

import com.jarq.TextAnalysisTest;
import com.jarq.enums.Path;
import com.jarq.model.FileContent;
import org.junit.*;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CharIteratorTest extends TextAnalysisTest {

    private Iterator<String> iterator;

    @Before
    public void setIterator() {
        try {
            FileContent fileContent = new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test1.txt");
            iterator = fileContent.charIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorWithProperParameters() {

        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
    }

    @Test
    public void testHasNext() throws IOException {

        Iterator<String> it = new CharIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt"));

        assertTrue(it.hasNext());

        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();

        assertTrue(it.hasNext());

        it.next();

        assertFalse(it.hasNext());
    }

    @Test
    public void testNext() {
        StringBuilder sb = new StringBuilder();

        iterator.next();

        assertEquals("a", iterator.next());

        while(iterator.hasNext()) {
            sb.append(iterator.next());
        }

        assertEquals("rdzodawnotemuwodleglejgalaktyceposzedlkotnadrzeczkeinic", sb.toString());
        assertEquals("", iterator.next());
    }
}