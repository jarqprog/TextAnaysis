package com.jarq.controllers;

import com.jarq.model.StatisticalAnalysis;
import com.jarq.view.View;

import java.util.NoSuchElementException;

public abstract class AnalysisController implements TextAnalyzer {

    private StatisticalAnalysis analysis;

    public void runAnalyze() {
        prepareToAnalyze();
        analyze();
    }

    protected abstract void analyze();

    protected AnalysisController(StatisticalAnalysis analysis) {
        this.analysis = analysis;
    }

    protected StatisticalAnalysis getAnalysis() {
        return analysis;
    }

    protected void showElementsQuantity() {
        if(getAnalysis().hasCharIterator()) {
            View.print("Char count: " + getAnalysis().size());
        } else {
            View.print("Word count: " + getAnalysis().size());
        }
    }

    protected void displaySeparator() {
        View.print("\n#####");
    }

    protected void prepareToAnalyze() {
        if(getAnalysis().size() == 0) {
            throw new NoSuchElementException("Analyzer must be recreated.");
        }
    }
}
