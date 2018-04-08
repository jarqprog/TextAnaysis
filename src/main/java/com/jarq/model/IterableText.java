package com.jarq.model;

import java.util.Iterator;

public interface IterableText<T> {

    Iterator<T> charIterator();
    Iterator<T> wordIterator();
    String getText();

}
