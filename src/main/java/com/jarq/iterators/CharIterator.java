package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.IOException;

public class CharIterator extends StringIterator {

    public CharIterator(FileContent fileContent) throws IOException {
        super(fileContent);
    }
}
