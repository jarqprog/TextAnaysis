package com.jarq.model;

import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import static org.junit.Assert.*;

import java.io.IOException;

public class FileContentTest {

    private FileContent fileContent;

    @Before
    public void setFileContent() {
        try {
            fileContent = new FileContent("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        fileContent = null;
    }

    @Test(expected = IOException.class)
    public void createFileContentWithNotExistingFile() throws IOException {
        new FileContent("file");
    }

    @Test(expected = IOException.class)
    public void createFileContentWithDirectoryInsteadOfFile() throws IOException {
        new FileContent("fake_file");
    }

    @Test(expected = IOException.class)
    public void createFileContentWithEmptyFile() throws IOException {
        new FileContent("empty_file.txt");
    }

    @Test
    public void testGetCharIterator() {
        assertNotNull(fileContent.charIterator());
        assertTrue(fileContent.charIterator() instanceof CharIterator);
    }

    @Test
    public void testGetWordIterator() {
        assertNotNull(fileContent.wordIterator());
        assertTrue(fileContent.wordIterator() instanceof WordIterator);
    }

    @Test
    public void testGetProperFilename() {
        assertEquals("test.txt", fileContent.getFilename());
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}