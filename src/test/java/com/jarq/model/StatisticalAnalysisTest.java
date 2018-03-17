package com.jarq.model;

import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class StatisticalAnalysisTest {

    private StatisticalAnalysis charAnalysis, wordAnalysis;

    @Before
    public void setUp() throws Exception {
        charAnalysis = new StatisticalAnalysis(new CharIterator(new FileContent("test.txt")));
        wordAnalysis = new StatisticalAnalysis(new WordIterator(new FileContent("test.txt")));
    }

    @After
    public void tearDown() {
        charAnalysis = null;
        wordAnalysis = null;
    }

    @Test(expected = IOException.class)
    public void testCreateWithEmptyFile() throws IOException {
        new StatisticalAnalysis(new CharIterator(new FileContent("empty_file.txt")));
    }

    @Test(expected = IOException.class)
    public void testCreateWithFakeFile() throws IOException {
        new StatisticalAnalysis(new CharIterator(new FileContent("fake_file")));
    }

    @Test(expected = NoSuchElementException.class)
    public void testCreateWithNumbersFile() throws NoSuchElementException, IOException {
        new StatisticalAnalysis(new CharIterator(new FileContent("numbers.txt")));
    }

    @Test
    public void testCountOf() throws IOException {

    }

    @Test
    public void testDictionarySize() throws IOException {
        Assert.assertEquals(6, (long) new StatisticalAnalysis(
                new CharIterator(new FileContent("test_two_words.txt"))).dictionarySize());
        Assert.assertEquals(2, (long) new StatisticalAnalysis(
                new WordIterator(new FileContent("test_two_words.txt"))).dictionarySize());
        Assert.assertEquals(31, (long) charAnalysis.dictionarySize());
        Assert.assertEquals(147, (long) wordAnalysis.dictionarySize());
    }

    @Test
    public void testSize() throws IOException {
        Assert.assertEquals(7, (long) new StatisticalAnalysis(
                new CharIterator(new FileContent("test_two_words.txt"))).size());
        Assert.assertEquals(2, (long) new StatisticalAnalysis(
                new WordIterator(new FileContent("test_two_words.txt"))).size());
        Assert.assertEquals(1031, (long) charAnalysis.size());
        Assert.assertEquals(268, (long) wordAnalysis.size());
        Assert.assertEquals(955386, (long) new StatisticalAnalysis(
                new CharIterator(new FileContent("test_malville_moby.txt"))).size());
        Assert.assertEquals(219048, (long) new StatisticalAnalysis(
                new WordIterator(new FileContent("test_malville_moby.txt"))).size());
    }

    @Test
    public void testSizeWithHugeFile() throws IOException {
        Assert.assertEquals(955386, (long) new StatisticalAnalysis(
                new CharIterator(new FileContent("test_malville_moby.txt"))).size());
        Assert.assertEquals(219048, (long) new StatisticalAnalysis(
                new WordIterator(new FileContent("test_malville_moby.txt"))).size());
    }

    @Test
    public void testOccurMoreThan() {
    }

    @Test
    public void testGetDictionary() throws IOException {
        StatisticalAnalysis words = new StatisticalAnalysis(
                new WordIterator(new FileContent("test_two_words.txt")));
        StatisticalAnalysis chars = new StatisticalAnalysis(
                new CharIterator(new FileContent("test_two_words.txt")));

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

        Assert.assertEquals(expectedWords, words.getDictionary());
        Assert.assertEquals(expectedChars, chars.getDictionary());
    }

    @Test
    public void testHasCharIterator() {
        Assert.assertTrue(charAnalysis.hasCharIterator());
        Assert.assertFalse(wordAnalysis.hasCharIterator());
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}