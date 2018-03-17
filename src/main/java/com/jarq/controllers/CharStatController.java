package com.jarq.controllers;

import com.jarq.enums.RegExpression;
import com.jarq.model.StatisticalAnalysis;

import java.util.Locale;
import java.util.Map;

public class CharStatController {

    private StatisticalAnalysis dataset;

    public static CharStatController getInstance(StatisticalAnalysis dataset) {
        return new CharStatController(dataset);
    }

    private CharStatController(StatisticalAnalysis dataset) {
        this.dataset = dataset;
        if(! this.dataset.hasCharIterator()) {
            throw new IllegalArgumentException("You should use dataset with CharIterator!");
        }
    }

    StatisticalAnalysis getDataset() {
        return dataset;
    }

    public String getRatioOfLettersOccurrence(String firstLetter, String secondLetter) {
            if(
                    ! firstLetter.matches(RegExpression.ONLY_LETTER.getRegex()) |
                    ! secondLetter.matches(RegExpression.ONLY_LETTER.getRegex())) {
                throw new IllegalArgumentException("use letters only!");
            }

        Float ratio = ((float) dataset.countOf(firstLetter) / (float) dataset.countOf(secondLetter));
        return String.format(Locale.US,"%s:%s count ratio: %.2f", firstLetter, secondLetter, ratio);
    }

    public String getPercentageVowelsOccurrence() {
        String[] vowels = {"a", "o", "i", "e", "u", "A", "O", "I", "E", "U"};
        Float percentage = ((float) dataset.countOf(vowels) / (float) dataset.size()) * 100f;
        return String.format(Locale.US,"vowels (percentage): %.2f", percentage);
    }

    public String getPercentageOccurrenceOfAllLetters() {
        Map<String,Integer> lettersOccurrences = dataset.getDictionary();
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        int elementsInLine = 7;
        for(Map.Entry<String,Integer> pair : lettersOccurrences.entrySet()) {
            String letter = pair.getKey();
            Float percentageOccurrence = ((float) pair.getValue() / (float) dataset.size())*100f;
            sb.append(String.format(Locale.US,"[%s ->%5.2f] ", letter, percentageOccurrence));
            if(counter % elementsInLine == 0) {
                sb.append("\n");
            }
            counter++;
        }
        return sb.toString();
    }

    public String getCharsQuantity() {
        return "Char count: " + dataset.size();
    }
}
