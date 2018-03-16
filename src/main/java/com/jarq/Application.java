package com.jarq;


import com.jarq.model.FileContent;
import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Application {
    public static void main( String[] args ) {

        Long startTime = System.currentTimeMillis();

        for(String filename : args) {
            analyzeTextFromFile(filename);
        }

        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        View.print("Benchmark time: " + benchmark + " secs");
    }

    private static void analyzeTextFromFile(String filename) {

        FileContent fileContent = null;
        try {
            fileContent = new FileContent(filename);
        } catch (IOException e) {
            View.print(e.getMessage());
            System.exit(1);
        }

        View.print(String.format("\n\nAnalyze data from file:\n\t-'%s'", filename));
        View.print("----------------------------------------------");
        Iterator<String> charIterator = fileContent.charIterator();
        Iterator<String> wordIterator = fileContent.wordIterator();
        StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);
        StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);
        analyzeCharacters(charAnalysis);
        analyzeWords(wordAnalysis);
    }

    private static void analyzeCharacters(StatisticalAnalysis charAnalysis) {
        if(! charAnalysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }

        showElementsQuantity(charAnalysis);
        showRatioOfStringElementsOccurrences("a", "e", charAnalysis);
        showPercentageVowelsOccurrence(charAnalysis);
        showPercentageOccurrenceOfAllLetters(charAnalysis);
    }

    private static void analyzeWords(StatisticalAnalysis wordAnalysis) {
        if(wordAnalysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with WordIterator!");
        }

        showElementsQuantity(wordAnalysis);
        showDictSize(wordAnalysis);
        showElementsUsedMoreThan(1, wordAnalysis);
        showWordOccurrence("love", wordAnalysis);
        showWordOccurrence("hate", wordAnalysis);
        showWordOccurrence("music", wordAnalysis);
    }

    private static void showRatioOfStringElementsOccurrences(String first, String second, StatisticalAnalysis analyser) {
        Float ratio = ((float) analyser.countOf(first) / (float) analyser.countOf(second));
        View.print(String.format("%s:%s count ratio: %.2f", first, second, ratio));
    }

    private static void showPercentageVowelsOccurrence(StatisticalAnalysis analyser) {
        if(! analyser.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
        String[] vowels = {"a", "o", "i", "e", "u", "A", "O", "I", "E", "U"};
        Float percentage = ((float) analyser.countOf(vowels) / (float) analyser.size()) * 100f;
        String toDisplay = String.format("vowels (percentage): %.2f", percentage);
        View.print(toDisplay);
    }

    private static void showPercentageOccurrenceOfAllLetters(StatisticalAnalysis analyser) {
        if(! analyser.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
        Map<String,Integer> lettersOccurrences = analyser.getDictionary();
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        int elementsInLine = 5;
        for(Map.Entry<String,Integer> pair : lettersOccurrences.entrySet()) {
            String letter = pair.getKey();
            Float percentageOccurrence = ((float) pair.getValue() / (float) analyser.size())*100f;
            sb.append(String.format("[%s ->%5.2f] ", letter, percentageOccurrence));
            if(counter % elementsInLine == 0) {
                sb.append("\n");
            }
            counter++;
        }
        View.print(sb.toString());
    }

    private static void showDictSize(StatisticalAnalysis analyser) {
        if(analyser.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with WordIterator!");
        }
        View.print(String.format("Dict size: %s", analyser.size()));
    }

    private static void showWordOccurrence(String element, StatisticalAnalysis analyser) {
        if(analyser.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with WordIterator!");
        }
        View.print(String.format("'%s' count: %s", element, analyser.countOf(element)));
    }

    private static void showElementsQuantity(StatisticalAnalysis analyser) {
        if(analyser.hasCharIterator()) {
            View.print("Char count: " + analyser.size());
        } else {
            View.print("Word count: " + analyser.size());
        }
    }

    private static void showElementsUsedMoreThan(Integer percentageOccurrence, StatisticalAnalysis analyser) {
        if(analyser.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with WordIterator!");
        }
        Integer occurrence = (int) ((float) analyser.size() * (0.01f * ((float) percentageOccurrence)));
        String toDisplay = String.format("Most used words (>%s percent): %s", percentageOccurrence, analyser.occurMoreThan(occurrence));
        View.print(toDisplay);
    }
}
