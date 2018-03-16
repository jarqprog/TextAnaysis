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

        for(String filename : filenames) {
            showFileInfo(filename);
            CharAnalysisController.getInstance(filename).runAnalyze();
            WordAnalysisController.getInstance(filename).runAnalyze();
        }
        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        View.print("Benchmark time: " + benchmark + " secs");
    }

    private void showFileInfo(String filename) {
        View.print(String.format("\n\nAnalyze data from file:\n\t-'%s'", filename));
        View.print("----------------------------------------------");
    }
}
