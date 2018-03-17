package com.jarq.controllers;

import com.jarq.model.FileContent;
import com.jarq.model.IterableText;
import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

import java.io.IOException;

public class ResultController implements Result {

    private String[] wordsToCalculateOccurrence = {"love", "hate", "music"};
    private Integer minimalWordOccurrenceThreshold = 1;  // percentage
    private String[] lettersToCalculateOccurrenceRatio = {"a", "e"};

    private String filename;
    private IterableText<String> iterable;
    private StringBuilder result;
    private CharStatController charStatController;
    private WordStatController wordStatController;

    public static ResultController getInstance(String filename) {
        return new ResultController(filename);
    }

    private ResultController(String filename) {
        try {
            iterable = new FileContent(filename);
            result = new StringBuilder();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.filename = filename;
        charStatController = CharStatController
                .getInstance(new StatisticalAnalysis(iterable.charIterator()));
        wordStatController = WordStatController
                .getInstance(new StatisticalAnalysis(iterable.wordIterator()));
    }

    private void buildResult() {
        result = new StringBuilder();
        result.append(getFileInfo());
        result.append(getNextLine());
        result.append(charStatController.getCharsQuantity());
        result.append(getNextLine());
        result.append(wordStatController.getWordsQuantity());
        result.append(getNextLine());
        result.append(wordStatController.getDictSize());
        result.append(getNextLine());
        result.append(wordStatController.getMostPopularWords(minimalWordOccurrenceThreshold));
        result.append(getNextLine());
        result.append(wordStatController.getWordOccurrence(wordsToCalculateOccurrence));
        result.append(getNextLine());
        result.append(charStatController.getPercentageVowelsOccurrence());
        result.append(getNextLine());
        result.append(charStatController
                .getRatioOfLettersOccurrence(lettersToCalculateOccurrenceRatio[0], lettersToCalculateOccurrenceRatio[1]));
        result.append(getNextLine());
        result.append(charStatController.getPercentageOccurrenceOfAllLetters());
        result.append(getNextLine());
    }

    @Override
    public void showResult() {
        buildResult();
        View.print(getResult());
    }

    @Override
    public String getResult() {
        if(result.length() > 0) {
            return result.toString();
        }
        return "[is empty]";
    }

    private String getFileInfo() {
        return String.format("==%s==", filename);
    }

    private String getNextLine() {
        return "\n";
    }
}
