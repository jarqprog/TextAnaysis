package com.jarq.model;

import com.jarq.enums.Path;
import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;

public class FileContentTest {

    private FileContent fileContent;

    @Before
    public void setFileContent() {
        try {
            fileContent = new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IOException.class)
    public void createFileContentWithNotExistingFile() throws IOException {
        new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "file");
    }

    @Test(expected = IOException.class)
    public void createFileContentWithDirectoryInsteadOfFile() throws IOException {
        new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "fake_file");
    }

    @Test(expected = IOException.class)
    public void createFileContentWithEmptyFile() throws IOException {
        new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "empty_file.txt");
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
        assertEquals(Path.RESOURCES_DIRECTORY.getPath() + "test.txt", fileContent.getFilename());
    }
}
