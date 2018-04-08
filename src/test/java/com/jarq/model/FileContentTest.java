package com.jarq.model;

import com.jarq.enums.Path;
import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.NoSuchElementException;

public class FileContentTest {

    private FileContent fileContent;

    @Before
    public void setFileContent() {

        String filename = Path.RESOURCES_DIRECTORY.getPath() + "test.txt";
        try {
            fileContent = new FileContent(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IOException.class)
    public void createFileContentUsingNotExistingFile() throws IOException {
        String notExisting = Path.RESOURCES_DIRECTORY.getPath() + "file";
        new FileContent(notExisting);
    }

    @Test(expected = IOException.class)
    public void createFileContentUsingDirectoryInsteadOfFile() throws IOException {
        String fakeFile = Path.RESOURCES_DIRECTORY.getPath() + "fake_file";
        new FileContent(fakeFile);
    }

    @Test(expected = IOException.class)
    public void createFileContentUsingEmptyFile() throws IOException {
        String emptyFile = Path.RESOURCES_DIRECTORY.getPath() + "empty_file";
        new FileContent(emptyFile);
    }

    @Test(expected = NoSuchElementException.class)
    public void createFileContentUsingFileThatNotContainsAnyLetters() throws IOException {
        String noLettersFile = Path.RESOURCES_DIRECTORY.getPath() + "numbers.txt";
        new FileContent(noLettersFile);
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
        String expectedFilename = Path.RESOURCES_DIRECTORY.getPath() + "test.txt";

        assertEquals(expectedFilename, fileContent.getFilename());
    }
}
