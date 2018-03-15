package com.jarq;

import java.util.Iterator;

public class FileContent implements IterableText {

    private String fileName;
    private static final String PATH_TO_FILES = "res/";

    public FileContent(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public Iterator<String> charIterator() {
        return null;
    }

    @Override
    public Iterator<String> wordIterator() {
        return null;
    }

    public String getFileName() {
        return fileName;
    }
}
