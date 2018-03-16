package com.jarq.controllers;

import com.jarq.enums.RegExpression;
import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

public class WordAnalysisController extends AnalysisController {

    private static String[] wordsToCalculateOccurrence = {"love", "hate", "music"};
    private static Integer minimalWordOccurrenceThreshold = 1;  // percentage

    private StatisticalAnalysis analysis;

    public static TextAnalyzer getInstance(String filename) {
        return new WordAnalysisController(filename);
    }

    private WordAnalysisController(String filename) {
        super(filename);
        this.analysis = new StatisticalAnalysis(getIterable().wordIterator());
    }

    @Override
    protected void prepareToAnalyze() {
        if(analysis.size() == 0) {
            this.analysis = new StatisticalAnalysis(getIterable().wordIterator());  // restart iterator
        }
    }
    @Override
    protected void analyze() {
        showElementsQuantity();
        showDictSize();
        showMostPopularWords();
        showWordOccurrence();
    }

    private void showDictSize() {
        View.print(String.format("Dict size: %s", analysis.dictionarySize()));
    }

    private void showWordOccurrence() {
        for(String word : wordsToCalculateOccurrence) {
            View.print(String.format("'%s' count: %s", word, analysis.countOf(word)));
        }
    }

    private void showMostPopularWords() {
        Integer occurrence =
                (int) ((float) analysis.size() * (0.01f * ((float) minimalWordOccurrenceThreshold)));
        String toDisplay = String.format(
                "Most used words (>%s percent): %s", minimalWordOccurrenceThreshold,
                analysis.occurMoreThan(occurrence));
        View.print(toDisplay);
    }

    private void showElementsQuantity() {
        View.print("Word count: " + analysis.size());
    }


    public static void setWordsToCalculateOccurrence(String[] words) {
        if(words.length == 2) {
            throw new IllegalArgumentException("at least one element is required");
        }
        for(String word : words) {
            if(! word.matches(RegExpression.ALL_LETTERS.getRegex())) {
                throw new IllegalArgumentException("word must contain letters only!");
            }
        }
        wordsToCalculateOccurrence = words;
    }


    public static void setMinimalWordOccurrenceThreshold(Integer threshold) {
        if(threshold < 1 | threshold > 99) {
            throw new IllegalArgumentException("type number between 1-99 (percentage value)");
        }
        minimalWordOccurrenceThreshold = threshold;
    }
}