package com.jarq.model;

import com.jarq.TextAnalysisTest;
import com.jarq.enums.Path;
import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;
import org.junit.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StatisticalAnalysisTest extends TextAnalysisTest {

    private StatisticalAnalysis charAnalysis, wordAnalysis;

    @Before
    public void setUp() throws IOException {
        charAnalysis = new StatisticalAnalysis(
                new CharIterator(new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test.txt")));
        wordAnalysis = new StatisticalAnalysis(
                new WordIterator(new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test.txt")));
    }

    @Test
    public void testDictionarySize() throws IOException {
        assertEquals(6, (long) new StatisticalAnalysis(new CharIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt"))).dictionarySize());

        assertEquals(2, (long) new StatisticalAnalysis(new WordIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt"))).dictionarySize());

        assertEquals(31, (long) charAnalysis.dictionarySize());

        assertEquals(147, (long) wordAnalysis.dictionarySize());
    }

    @Test
    public void testSize() throws IOException {
        assertEquals(7, (long) new StatisticalAnalysis(new CharIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt"))).size());

        assertEquals(2, (long) new StatisticalAnalysis(new WordIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt"))).size());

        assertEquals(1031, (long) charAnalysis.size());

        assertEquals(268, (long) wordAnalysis.size());

        assertEquals(955386, (long) new StatisticalAnalysis(new CharIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_malville_moby.txt"))).size());

        assertEquals(219048, (long) new StatisticalAnalysis(new WordIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_malville_moby.txt"))).size());
    }

    @Test
    public void testSizeUsingHugeFile() throws IOException {
        assertEquals(955386, (long) new StatisticalAnalysis(new CharIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_malville_moby.txt"))).size());

        assertEquals(219048, (long) new StatisticalAnalysis(new WordIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_malville_moby.txt"))).size());
    }

    @Test
    public void testGetDictionary() throws IOException {
        StatisticalAnalysis words = new StatisticalAnalysis(new WordIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt")));
        StatisticalAnalysis chars = new StatisticalAnalysis(new CharIterator(
                new FileContent(Path.RESOURCES_DIRECTORY.getPath() + "test_two_words.txt")));

        Map<String,Integer> expectedWords = new HashMap<>();
        Map<String,Integer> expectedChars = new HashMap<>();

        expectedWords.put("there", 1);
        expectedWords.put("is", 1);

        expectedChars.put("t", 1);
        expectedChars.put("h", 1);
        expectedChars.put("e", 2);
        expectedChars.put("r", 1);
        expectedChars.put("i", 1);
        expectedChars.put("s", 1);

        assertEquals(expectedWords, words.getDictionary());
        assertEquals(expectedChars, chars.getDictionary());
    }

    @Test
    public void testHasCharIterator() {
        assertTrue(charAnalysis.hasCharIterator());
        assertFalse(wordAnalysis.hasCharIterator());
    }
}