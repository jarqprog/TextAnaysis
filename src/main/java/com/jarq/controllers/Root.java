package com.jarq.controllers;


import com.jarq.model.FileContent;
import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

import java.io.IOException;
import java.util.Iterator;

public class Root {

    public static Root getInstance(String[] filenames) {
        return new Root(filenames);
    }

    private final String[] filenames;

    private Root(String[] filenames){
        this.filenames = filenames;
    }

    public void runApp() {
        Long startTime = System.currentTimeMillis();

        for(String filename : filenames) {
            analyzeTextFromFile(filename);
        }

        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        View.print("Benchmark time: " + benchmark + " secs");
    }

    private void analyzeTextFromFile(String filename) {

        FileContent fileContent = null;
        try {
            fileContent = new FileContent(filename);
        } catch (IOException e) {
            View.print(e.getMessage());
            System.exit(1);
        }

        Iterator<String> charIterator = fileContent.charIterator();
        Iterator<String> wordIterator = fileContent.wordIterator();

        View.print(String.format("\n\nAnalyze data from file:\n\t-'%s'", filename));
        View.print("----------------------------------------------");

        CharAnalysisController.getInstance(new StatisticalAnalysis(charIterator))
                .runAnalyze();
        WordAnalysisController.getInstance(new StatisticalAnalysis(wordIterator))
                .runAnalyze();

//        TextAnalyzer ta = WordAnalysisController.getInstance(new StatisticalAnalysis(wordIterator));
//        ta.runAnalyze();  // restart iterator?
    }
}
