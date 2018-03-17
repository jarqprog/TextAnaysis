package com.jarq.controllers;

import com.jarq.managers.FileWriterManager;
import com.jarq.managers.WriterManager;
import com.jarq.view.View;

import java.util.Locale;

public class Root {

    private static final String OUTPUT_FILE_PATH = "OUTPUT_RESULT.md";
    private WriterManager writer;

    public static Root getInstance(String[] filenames) {
        return new Root(filenames);
    }

    private final String[] filenames;

    private Root(String[] filenames) {
        if(filenames.length == 0) {
            throw new IllegalArgumentException("please provide file names for text analysis");
        }
        this.filenames = filenames;
        writer = FileWriterManager.getInstance(OUTPUT_FILE_PATH);
    }

    public void runApp() {
        Long startTime = System.currentTimeMillis();

        performAnalyze();

        Double millisecondToSecondModifier = 0.001;
        Double benchmark = ((System.currentTimeMillis() - startTime)*millisecondToSecondModifier);
        String benchmarkInfo = String.format(Locale.US,"Benchmark time: %s secs\n", benchmark);
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
