package com.jarq.controllers;

import com.jarq.enums.RegExpression;
import com.jarq.model.StatisticalAnalysis;

public class WordStatController {

    private StatisticalAnalysis dataset;

    public static WordStatController getInstance(StatisticalAnalysis dataset) {
        return new WordStatController(dataset);
    }

    private WordStatController(StatisticalAnalysis dataset) {
        this.dataset = dataset;
        if(this.dataset.hasCharIterator()) {
            throw new IllegalArgumentException("You should use dataset with WordIterator!");
        }
    }

    StatisticalAnalysis getDataset() {
        return dataset;
    }

    public String getDictSize() {
        return String.format("Dict size: %s", dataset.dictionarySize());
    }

    public String getWordOccurrence(String[] words) {
        validateWords(words);
        StringBuilder sb = new StringBuilder();
        Integer counter = 0;
        for(String word : words) {
            sb.append(String.format("'%s' count: %s", word, dataset.countOf(word)));
            if(counter != words.length-1) {
                sb.append("\n");
            }
            counter++;
        }

        return sb.toString();
    }

    public String getMostPopularWords(Integer minimalWordOccurrenceThreshold) {
        validateMinimalWordOccurrenceThreshold(minimalWordOccurrenceThreshold);
        Integer occurrence =
                (int) ((float) dataset.size() * (0.01f * ((float) minimalWordOccurrenceThreshold)));
        return String.format(
                "Most used words (>%s percent): %s", minimalWordOccurrenceThreshold,
                dataset.occurMoreThan(occurrence));
    }

    public String getWordsQuantity() {
        return "Word count: " + dataset.size();
    }

    private void validateWords(String[] words) {
        if (words.length == 0) {
            throw new IllegalArgumentException("at least one element is required");
        }
        for (String word : words) {
            if (!word.matches(RegExpression.ALL_LETTERS.getRegex())) {
                throw new IllegalArgumentException("word must contain letters only!");
            }
        }
    }

    private void validateMinimalWordOccurrenceThreshold(Integer threshold) {
        if(threshold < 1 | threshold > 99) {
            throw new IllegalArgumentException("type number between 1-99 (percentage value)");
        }
    }
}
