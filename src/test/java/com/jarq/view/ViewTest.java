package com.jarq.view;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import static org.junit.Assert.*;

public class ViewTest {

    @Test
    public void print() {
    }

    @Test
    public void print1() {
    }

    @Test
    public void print2() {
    }

    @Test
    public void print3() {
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}