package com.jarq.controllers;

import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

public class WordAnalysisController extends AnalysisController {

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
        String[] wordsToCheck = new String[] {"love", "hate", "music"};
        for(String word : wordsToCheck) {
            View.print(String.format("'%s' count: %s", word, analysis.countOf(word)));
        }
    }

    private void showMostPopularWords() {
        Integer popularityFilter = 1;  // minimum percentage word occurrence in text
        Integer occurrence =
                (int) ((float) analysis.size() * (0.01f * ((float) popularityFilter)));
        String toDisplay = String.format(
                "Most used words (>%s percent): %s", popularityFilter,
                analysis.occurMoreThan(occurrence));
        View.print(toDisplay);
    }

    private void showElementsQuantity() {
        View.print("Word count: " + analysis.size());
    }
}