package com.jarq.controllers;

import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

public class WordAnalysisController extends AnalysisController {

    public static TextAnalyzer getInstance(StatisticalAnalysis analysis) {
        return new WordAnalysisController(analysis);
    }

    private WordAnalysisController(StatisticalAnalysis analysis) {
        super(analysis);
        if(analysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with WordIterator!");
        }
    }

    protected void analyze() {

        displaySeparator();
        showElementsQuantity();
        showDictSize();
        showMostPopularWords();
        showWordOccurrence();
    }

    private void showDictSize() {
        View.print(String.format("Dict size: %s", getAnalysis().dictionarySize()));
    }

    private void showWordOccurrence() {
        String[] wordsToCheck = new String[] {"love", "hate", "music"};
        for(String word : wordsToCheck) {
            View.print(String.format("'%s' count: %s", word, getAnalysis().countOf(word)));
        }
    }

    private void showMostPopularWords() {
        Integer popularityFilter = 1;  // minimum percentage word occurrence in text
        Integer occurrence =
                (int) ((float) getAnalysis().size() * (0.01f * ((float) popularityFilter)));
        String toDisplay = String.format(
                "Most used words (>%s percent): %s", popularityFilter,
                getAnalysis().occurMoreThan(occurrence));
        View.print(toDisplay);
    }
}