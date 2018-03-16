package com.jarq;

import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;
import com.jarq.model.FileContent;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

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
    public void destroyFileContent() {
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
    public void getCharIterator() {
        Assert.assertNotNull(fileContent.charIterator());
    }

    @Test
    public void getWordIterator() {
        Assert.assertNotNull(fileContent.wordIterator());
    }

    @Test
    public void testCharIteratorType() {
        Assert.assertTrue(fileContent.charIterator() instanceof CharIterator);
    }

    @Test
    public void testWordIteratorType() {
        Assert.assertTrue(fileContent.wordIterator() instanceof WordIterator);
    }

    @Test
    public void testGetProperFilename() {
        Assert.assertEquals("test.txt", fileContent.getFilename());
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}