package com.jarq.controllers;

import com.jarq.enums.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResultControllerTest {

    private final String filename = Path.RESOURCES_DIRECTORY.getPath() + "test.txt";
    private Result resultController;

    @Before
    public void setUp() {
        resultController = ResultController.getInstance(filename);
    }

    @After
    public void tearDown() {
        resultController = null;
    }

    @Test
    public void getInstance() {
        assertNotNull(ResultController.getInstance(filename));
    }

    @Test
    public void showResult() {
    }

    @Test
    public void getResult() {
        String expected = "==" + Path.RESOURCES_DIRECTORY.getPath() + "test.txt==\n" +
                "Char count: 1031\n" +
                "Word count: 268\n" +
                "Dict size: 147\n" +
                "Most used words (>1 percent): [I, a, and, been, figure, had, in, is, it, me, music, no, not, of, old, the, to, was, where]\n" +
                "'love' count: 1\n" +
                "'hate' count: 0\n" +
                "'music' count: 3\n" +
                "vowels (percentage): 38.99\n" +
                "a:e count ratio: 0.55\n" +
                "[A -> 0.39] [B -> 0.10] [F -> 0.10] [H -> 0.10] [I -> 1.16] [T -> 0.58] [a -> 7.57] \n" +
                "[b -> 1.55] [c -> 1.94] [d -> 5.14] [e ->13.87] [f -> 1.94] [g -> 2.23] [h -> 5.53] \n" +
                "[i -> 5.63] [j -> 0.10] [k -> 0.58] [l -> 2.91] [m -> 2.62] [n -> 7.95] [o -> 7.37] \n" +
                "[p -> 0.97] [q -> 0.10] [r -> 5.53] [s -> 6.11] [t -> 8.73] [u -> 3.01] [v -> 1.07] \n" +
                "[w -> 3.78] [x -> 0.10] [y -> 1.26] \n";
        assertEquals(expected, resultController.getResult());
    }
}
