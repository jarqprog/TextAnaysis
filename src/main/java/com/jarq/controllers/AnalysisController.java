package com.jarq.controllers;

import com.jarq.model.FileContent;
import com.jarq.model.IterableText;

import java.io.IOException;


public abstract class AnalysisController implements TextAnalyzer {

    private IterableText<String> iterable;

    AnalysisController(String filename) {
        try {
            this.iterable = new FileContent(filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void runAnalyze() {
        prepareToAnalyze();
        analyze();
    }

    protected abstract void prepareToAnalyze();  // restart iterator

    protected abstract void analyze();

    protected IterableText<String> getIterable() {
        return iterable;
    }
}
