package com.jarq.controllers;

import com.jarq.enums.Path;
import com.jarq.model.FileContent;
import com.jarq.model.StatisticalAnalysis;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CharStatControllerTest {

    private CharStatController controller;
    private final String filename = Path.RESOURCES_DIRECTORY.getPath() + "test.txt";
    private final String hugeFile = Path.RESOURCES_DIRECTORY.getPath() + "test_dickens_great.txt";
    private final String[] lettersToCalculateOccurrenceRatio = {"a", "e"};

    @Before
    public void setUp() throws IOException {
        FileContent fileContent = new FileContent(filename);
        Iterator<String> iterator = fileContent.charIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        controller = CharStatController.getInstance(analysis);
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getInstanceCharStatController() throws IOException {
        assertNotNull(controller);
        assertNotNull(controller);
        assertNotNull(controller.getDataset());
        assertTrue(controller.getDataset() instanceof StatisticalAnalysis);
        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.charIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        CharStatController bigDataController = CharStatController.getInstance(analysis);
        assertNotNull(bigDataController);
        assertNotNull(bigDataController.getDataset());
        assertTrue(bigDataController.getDataset() instanceof StatisticalAnalysis);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceCharStatControllerWithIncorrectArgument()
            throws IllegalArgumentException, IOException {

        FileContent fileContent = new FileContent(filename);
        Iterator<String> iterator = fileContent.wordIterator();  // wordIterator - incorrect
        StatisticalAnalysis fakeAnalysis = new StatisticalAnalysis(iterator);
        CharStatController.getInstance(fakeAnalysis);
    }

    @Test
    public void getDataset() {
        assertNotNull(controller.getDataset());
        assertNotNull(controller.getDataset());
        assertTrue(controller.getDataset() instanceof StatisticalAnalysis);
    }

    @Test
    public void getRatioOfLettersOccurrence() {
        String expected = "a:e count ratio: 0.55";
        assertEquals(
                expected,
                controller.getRatioOfLettersOccurrence(
                        lettersToCalculateOccurrenceRatio[0],
                        lettersToCalculateOccurrenceRatio[1]));
    }

    @Test
    public void getPercentageVowelsOccurrence() throws IOException {
        String expected = "vowels (percentage): 38.99";

        assertEquals(
                expected,
                controller.getPercentageVowelsOccurrence());

        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.charIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        CharStatController bigDataController = CharStatController.getInstance(analysis);

        expected = "vowels (percentage): 38.16";

        assertEquals(
                expected,
                bigDataController.getPercentageVowelsOccurrence());
    }

    @Test
    public void getPercentageOccurrenceOfAllLetters() {
        String expected = "[A -> 0.39] [B -> 0.10] [F -> 0.10] [H -> 0.10] " +
                "[I -> 1.16] [T -> 0.58] [a -> 7.57] \n" +
                "[b -> 1.55] [c -> 1.94] [d -> 5.14] [e ->13.87] [f -> 1.94] " +
                "[g -> 2.23] [h -> 5.53] \n" +
                "[i -> 5.63] [j -> 0.10] [k -> 0.58] [l -> 2.91] [m -> 2.62] " +
                "[n -> 7.95] [o -> 7.37] \n" +
                "[p -> 0.97] [q -> 0.10] [r -> 5.53] [s -> 6.11] [t -> 8.73] " +
                "[u -> 3.01] [v -> 1.07] \n" +
                "[w -> 3.78] [x -> 0.10] [y -> 1.26] ";

        assertEquals(
                expected,
                controller.getPercentageOccurrenceOfAllLetters());
    }

    @Test
    public void getCharsQuantity() throws IOException {
        String expected = "Char count: 1031";

        assertEquals(
                expected,
                controller.getCharsQuantity());

        FileContent fileContent = new FileContent(hugeFile);
        Iterator<String> iterator = fileContent.charIterator();
        StatisticalAnalysis analysis = new StatisticalAnalysis(iterator);
        CharStatController bigDataController = CharStatController.getInstance(analysis);
        expected = "Char count: 761672";

        assertEquals(
                expected,
                bigDataController.getCharsQuantity());
    }
}