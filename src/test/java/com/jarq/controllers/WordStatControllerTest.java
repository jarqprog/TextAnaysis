package com.jarq.controllers;

import com.jarq.TextAnalysisTest;
import com.jarq.enums.Path;
import com.jarq.model.FileContent;
import com.jarq.model.StatisticalAnalysis;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Iterator;

public class WordStatControllerTest extends TextAnalysisTest {

    private WordStatController controller;
    private final String filename = Path.RESOURCES_DIRECTORY.getPath() + "test.txt";
    private final String[] wordsToCalculateOccurrence = {"love", "hate", "music"};

    @Before
    public void setUp() throws IOException {

        FileContent fileContent = new FileContent(filename);
        Iterator<String> iterator = fileContent.wordIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        controller = WordStatController.getInstance(analysis);
    }

    @Test
    public void getInstanceWordStatController() {

        assertNotNull(controller);
        assertNotNull(controller.getDataset());
        assertTrue(controller.getDataset() instanceof StatisticalAnalysis);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceWordStatControllerWithIncorrectArgument()
            throws IllegalArgumentException, IOException {

        FileContent fileContent = new FileContent(filename);
        Iterator<String> iterator = fileContent.charIterator();  // charIterator - incorrect
        StatisticalAnalysis fakeAnalysis = new StatisticalAnalysis(iterator);

        WordStatController.getInstance(fakeAnalysis);  // should throw exception
    }

    @Test
    public void getDataset() {
        assertNotNull(controller.getDataset());
        assertNotNull(controller.getDataset());
        assertTrue(controller.getDataset() instanceof StatisticalAnalysis);
    }

    @Test
    public void getDictSize() {
        String expected = "Dict size: 147";

        assertEquals(expected, controller.getDictSize());
    }

    @Test
    public void getWordOccurrence() {
        String expected = "'love' count: 1\n" +
                "'hate' count: 0\n" +
                "'music' count: 3";

        assertEquals(
                expected , controller.getWordOccurrence(wordsToCalculateOccurrence));
    }

    @Test
    public void getMostPopularWords() {
        final Integer minimalWordOccurrenceThreshold = 1;  // percentage
        String expected = "Most used words (>1 percent): [I, a, and, been, " +
                "figure, had, in, is, it, me, music, no, not, of, old, the, to, was, where]";

        assertEquals(
                expected ,controller.getMostPopularWords(minimalWordOccurrenceThreshold));
    }

    @Test
    public void getWordsQuantity() {
        String expected = "Word count: 268";

        assertEquals(
                expected , controller.getWordsQuantity());
    }
}