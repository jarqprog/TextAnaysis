package com.jarq.controllers;

import com.jarq.managers.FileWriterManager;
import com.jarq.managers.WriterManager;
import com.jarq.view.View;

public class Root {

    private static final String OUTPUT_FILE_PATH = "OUTPUT_RESULT.md";
    private WriterManager writer;

    public static Root getInstance(String[] filenames) {
        return new Root(filenames);
    }

    private final String[] filenames;

    private Root(String[] filenames){
        this.filenames = filenames;
        writer = FileWriterManager.getInstance(OUTPUT_FILE_PATH);
    }

    public void runApp() {
        Long startTime = System.currentTimeMillis();

        performAnalyze();

        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        String benchmarkInfo = String.format("Benchmark time: %s secs", benchmark);
        View.print(benchmarkInfo);
        writer.write(benchmarkInfo, true);
    }

    private void performAnalyze() {
        for(String filename : filenames) {
            Result result = ResultController.getInstance(filename);
            result.showResult();
            writer.write(result.getResult(), true);
        }
    }
}
