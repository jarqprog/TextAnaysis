package com.jarq;

import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;


public abstract class TextAnalysisTest {

    @Rule
    public MethodRule watchman = new TestWatchman() {

        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_RED = "\u001B[31m";
        private static final String ANSI_GREEN = "\u001B[32m";

        public void starting(FrameworkMethod method) {
            System.out.println(String.format("Test %s is running.", method.getName()));
        }
        public void succeeded(FrameworkMethod method) {
            System.out.println(String.format(ANSI_GREEN + "Test %s successfully run." + ANSI_GREEN + ANSI_RESET, method.getName()));
        }
        public void failed(Throwable e, FrameworkMethod method) {
            System.out.println(String.format(ANSI_RED + "Test %s failed with %s reason." + ANSI_RED + ANSI_RESET,
                    method.getName(), e.getMessage()));
        }
    };
}
