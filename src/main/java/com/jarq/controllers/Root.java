package com.jarq.controllers;

import com.jarq.view.View;

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

        performAnalyze();  // with default settings
        CharAnalysisController.setLettersToCalculateOccurrenceRatio(new String[]{"i", "o"});
        WordAnalysisController.setMinimalWordOccurrenceThreshold(4);
        WordAnalysisController.setWordsToCalculateOccurrence(new String[]{"drink", "rock", "sex"});
        performAnalyze();  // new settings
        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        showSeparator();
        View.print("Benchmark time: " + benchmark + " secs");
        showSeparator();
    }

    private void performAnalyze() {
        for(String filename : filenames) {
            showSeparator();
            showFileInfo(filename);
            CharAnalysisController.getInstance(filename).runAnalyze();
            WordAnalysisController.getInstance(filename).runAnalyze();
        }
    }

    private void showFileInfo(String filename) {
        View.print(String.format("Analyze data from file '%s'", filename));
        showSeparator();
    }

    private void showSeparator() {
        View.print("-----------------------------------------------------------");
    }
}
