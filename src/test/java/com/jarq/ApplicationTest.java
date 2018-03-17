package com.jarq;

import com.jarq.controllers.Root;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void testMain() {

        String[] args = {"text.txt"};

        assertNotNull(Root.getInstance(args));
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };
}
