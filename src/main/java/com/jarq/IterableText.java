package com.jarq;

import java.util.Iterator;

public interface IterableText<String> {

    Iterator<String> charIterator();
    Iterator<String> wordIterator();

}
