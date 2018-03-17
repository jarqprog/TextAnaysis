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

        performAnalyze();

        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        View.print("Benchmark time: " + benchmark + " secs");
    }

    private void performAnalyze() {
        for(String filename : filenames) {
            ResultController.getInstance(filename).showResult();
        }
    }
}
