package com.jarq.controllers;

import com.jarq.enums.RegExpression;
import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

import java.util.Map;

public class CharAnalysisController extends AnalysisController {

    private static String[] lettersToCalculateOccurrenceRatio = {"a", "e"};

    private StatisticalAnalysis analysis;

    public static TextAnalyzer getInstance(String filename) {
        return new CharAnalysisController(filename);
    }

    private CharAnalysisController(String filename) {
        super(filename);
        this.analysis = new StatisticalAnalysis(getIterable().charIterator());
    }

    @Override
    protected void prepareToAnalyze() {
        if(analysis.size() == 0) {
            this.analysis = new StatisticalAnalysis(getIterable().charIterator());  // restart iterator
        }
    }

    protected void analyze() {
        showElementsQuantity();
        showRatioOfLettersOccurrence();
        showPercentageVowelsOccurrence();
        showPercentageOccurrenceOfAllLetters();
    }

    private void showRatioOfLettersOccurrence() {
        String first = lettersToCalculateOccurrenceRatio[0];
        String second = lettersToCalculateOccurrenceRatio[1];

        Float ratio = ((float) analysis.countOf(first) / (float) analysis.countOf(second));
        View.print(String.format("%s:%s count ratio: %.2f", first, second, ratio));
    }

    private void showPercentageVowelsOccurrence() {
        if(! analysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
        String[] vowels = {"a", "o", "i", "e", "u", "A", "O", "I", "E", "U"};
        Float percentage = ((float) analysis.countOf(vowels) / (float) analysis.size()) * 100f;
        String toDisplay = String.format("vowels (percentage): %.2f", percentage);
        View.print(toDisplay);
    }

    private void showPercentageOccurrenceOfAllLetters() {
        if(! analysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with CharIterator!");
        }
        Map<String,Integer> lettersOccurrences = analysis.getDictionary();
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        int elementsInLine = 7;
        for(Map.Entry<String,Integer> pair : lettersOccurrences.entrySet()) {
            String letter = pair.getKey();
            Float percentageOccurrence = ((float) pair.getValue() / (float) analysis.size())*100f;
            sb.append(String.format("[%s ->%5.2f] ", letter, percentageOccurrence));
            if(counter % elementsInLine == 0) {
                sb.append("\n");
            }
            counter++;
        }
        View.print(sb.toString());
    }

    private void showElementsQuantity() {
        View.print("Char count: " + analysis.size());
    }

    public static void setLettersToCalculateOccurrenceRatio(String[] letters) {
        if(letters.length != 2) {
            throw new IllegalArgumentException("only two elements required");
        }
        for(String letter : letters) {
            if(! letter.matches(RegExpression.ONLY_LETTER.getRegex())) {
                throw new IllegalArgumentException("letters must contain letters only!");
            }
        }
        lettersToCalculateOccurrenceRatio = letters;
    }
}
