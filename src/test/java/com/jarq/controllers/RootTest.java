package com.jarq.controllers;

import com.jarq.TextAnalysisTest;
import com.jarq.enums.Path;
import org.junit.Test;

import static org.junit.Assert.*;

public class RootTest extends TextAnalysisTest {

    private final String[] args = {
            Path.RESOURCES_DIRECTORY.getPath() + "test.txt",
            Path.RESOURCES_DIRECTORY.getPath() + "test_two_words"};

    @Test
    public void getInstance() {
        assertNotNull(Root.getInstance(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceWithNoFileNames() {
        Root.getInstance(new String[]{});
    }

}