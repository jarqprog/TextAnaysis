package com.jarq;

import com.jarq.controllers.Root;
import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest extends TextAnalysisTest {

    @Test
    public void testMain() {

        String[] args = {"text.txt"};

        assertNotNull(Root.getInstance(args));
    }
}
