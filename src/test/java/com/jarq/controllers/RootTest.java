package com.jarq.controllers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import static org.junit.Assert.*;

public class RootTest {

    private final String[] args = {"test.txt", "test_two_words"};

    @Test
    public void getInstance() {
        assertNotNull(Root.getInstance(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceWithNoFileNames() {
        Root.getInstance(new String[]{});
    }

    @Test
    public void runApp() {
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}