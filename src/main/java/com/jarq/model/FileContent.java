package com.jarq.model;

import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;

import java.io.*;
import java.util.Iterator;

public class FileContent implements IterableText<String> {

    private String filename;

    public FileContent(String filename) throws IOException {
        this(new File(filename));
        this.filename = filename;
    }

    private FileContent(File file) throws IOException {
        if(!file.exists())
            throw new FileNotFoundException("File doesn't exist: " + file.getPath());
        if(!file.isFile())
            throw new IOException("Given file is not of type 'file': " + file.getPath());
        if(file.length() == 0)
            throw new IOException("Given file empty: " + file.getPath());
    }

    @Override
    public Iterator<String> charIterator() {
        try {
            return new CharIterator(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    public Iterator<String> wordIterator() {
        try {
            return new WordIterator(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public String getFilename() {
        return filename;
    }

}
