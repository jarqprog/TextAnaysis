package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.IOException;

public class WordIterator extends StringIterator {

    public WordIterator(FileContent fileContent) throws IOException {
        super(fileContent);
    }
}
