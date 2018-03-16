package com.jarq.model;

import java.util.Iterator;

public interface IterableText<String> {

    Iterator<String> charIterator();
    Iterator<String> wordIterator();

}
