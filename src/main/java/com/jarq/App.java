package com.jarq;


import com.jarq.view.View;

import java.io.IOException;
import java.util.Iterator;

public class App {
    public static void main( String[] args ) {
        Long start = System.currentTimeMillis();

        for(String filename : args) {
            analyzeTextFromFile(filename);
        }

        Double benchmark = ((System.currentTimeMillis() - start)*0.001);
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
        View.print("Char count: " + charAnalysis.size());
    }

    private static void analyzeWords(StatisticalAnalysis wordAnalysis) {
        if(wordAnalysis.hasCharIterator()) {
            throw new IllegalArgumentException("You should use analysis with WordIterator!");
        }
        View.print("Word count: " + wordAnalysis.size());
        View.print("Dict size: " + wordAnalysis.dictionarySize());
        View.print(wordAnalysis.getDictionary());
    }
}
