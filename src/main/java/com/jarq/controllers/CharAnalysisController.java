package com.jarq.controllers;

import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

import java.util.Map;

public class CharAnalysisController extends AnalysisController {

    public static TextAnalyzer getInstance(StatisticalAnalysis analysis) {
        return new CharAnalysisController(analysis);
    }

    private CharAnalysisController(StatisticalAnalysis analysis) {
        super(analysis);
        if(! analysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
    }

    protected void analyze() {

        displaySeparator();
        showElementsQuantity();
        showRatioOfLettersOccurrence();
        showPercentageVowelsOccurrence();
        showPercentageOccurrenceOfAllLetters();
    }

    private void showRatioOfLettersOccurrence() {
        String first = "a";
        String second = "e";

        Float ratio = ((float) getAnalysis().countOf(first) / (float) getAnalysis().countOf(second));
        View.print(String.format("%s:%s count ratio: %.2f", first, second, ratio));
    }

    private void showPercentageVowelsOccurrence() {
        if(! getAnalysis().hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
        String[] vowels = {"a", "o", "i", "e", "u", "A", "O", "I", "E", "U"};
        Float percentage = ((float) getAnalysis().countOf(vowels) / (float) getAnalysis().size()) * 100f;
        String toDisplay = String.format("vowels (percentage): %.2f", percentage);
        View.print(toDisplay);
    }

    private void showPercentageOccurrenceOfAllLetters() {
        if(! getAnalysis().hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
        Map<String,Integer> lettersOccurrences = getAnalysis().getDictionary();
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        int elementsInLine = 5;
        for(Map.Entry<String,Integer> pair : lettersOccurrences.entrySet()) {
            String letter = pair.getKey();
            Float percentageOccurrence = ((float) pair.getValue() / (float) getAnalysis().size())*100f;
            sb.append(String.format("[%s ->%5.2f] ", letter, percentageOccurrence));
            if(counter % elementsInLine == 0) {
                sb.append("\n");
            }
            counter++;
        }
        View.print(sb.toString());
    }
}
