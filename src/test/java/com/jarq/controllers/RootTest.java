package com.jarq.controllers;

import com.jarq.TextAnalysisTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class RootTest extends TextAnalysisTest {

    private final String[] args = {"test.txt", "test_two_words"};

    @Test
    public void getInstance() {
        assertNotNull(Root.getInstance(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceWithNoFileNames() {
        Root.getInstance(new String[]{});
    }

}