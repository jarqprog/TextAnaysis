package com.jarq.controllers;

import com.jarq.enums.Path;
import com.jarq.model.FileContent;
import com.jarq.model.StatisticalAnalysis;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Iterator;

public class WordStatControllerTest {

    private WordStatController controller;
    private final String filename = Path.RESOURCES_DIRECTORY.getPath() + "test.txt";
    private final String hugeFile = Path.RESOURCES_DIRECTORY.getPath() + "test_dickens_great.txt";
    private final String[] wordsToCalculateOccurrence = {"love", "hate", "music"};
    private final Integer minimalWordOccurrenceThreshold = 1;  // percentage

    @Before
    public void setUp() throws IOException {
        FileContent fileContent = new FileContent(filename);
        Iterator<String> iterator = fileContent.wordIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        controller = WordStatController.getInstance(analysis);
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getInstanceWordStatController() throws IOException {

        assertNotNull(controller);
        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.wordIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        WordStatController bigDataController = WordStatController.getInstance(analysis);
        assertNotNull(bigDataController);
        assertNotNull(bigDataController.getDataset());
        assertTrue(bigDataController.getDataset() instanceof StatisticalAnalysis);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceWordStatControllerWithIncorrectArgument()
            throws IllegalArgumentException, IOException {

        FileContent fileContent = new FileContent(filename);
        Iterator<String> iterator = fileContent.charIterator();  // charIterator - incorrect
        StatisticalAnalysis fakeAnalysis = new StatisticalAnalysis(iterator);
        WordStatController.getInstance(fakeAnalysis);
    }

    @Test
    public void getDataset() {
        assertNotNull(controller.getDataset());
        assertNotNull(controller.getDataset());
        assertTrue(controller.getDataset() instanceof StatisticalAnalysis);
    }

    @Test
    public void getDictSize() throws IOException {
        String expected = "Dict size: 147";
        assertEquals(expected, controller.getDictSize());

        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.wordIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        WordStatController bigDataController = WordStatController.getInstance(analysis);
        expected = "Dict size: 11678";
        assertEquals(expected, bigDataController.getDictSize());
    }

    @Test
    public void getWordOccurrence() throws IOException {
        String expected = "'love' count: 1\n" +
                "'hate' count: 0\n" +
                "'music' count: 3";
        assertEquals(
                expected , controller.getWordOccurrence(wordsToCalculateOccurrence));

        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.wordIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        WordStatController bigDataController = WordStatController.getInstance(analysis);
        expected = "'love' count: 53\n" +
                "'hate' count: 4\n" +
                "'music' count: 4";
        assertEquals(
                expected , bigDataController.getWordOccurrence(wordsToCalculateOccurrence));
    }

    @Test
    public void getMostPopularWords() {
        String expected = "Most used words (>1 percent): [I, a, and, been, " +
                "figure, had, in, is, it, me, music, no, not, of, old, the, to, was, where]";

        assertEquals(
                expected ,controller.getMostPopularWords(minimalWordOccurrenceThreshold));
    }

    @Test
    public void getWordsQuantity() throws IOException {
        String expected = "Word count: 268";
        assertEquals(
                expected , controller.getWordsQuantity());

        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.wordIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        WordStatController bigDataController = WordStatController.getInstance(analysis);
        expected = "Word count: 188917";
        assertEquals(
                expected , bigDataController.getWordsQuantity());
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };

}