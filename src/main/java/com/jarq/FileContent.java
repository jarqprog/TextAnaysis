package com.jarq;

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
        if( ! file.exists() )
            throw new FileNotFoundException( "File doesn't exist: " + file.getPath() );
        if( ! file.isFile() )
            throw new IOException( "File is not of type 'file': " + file.getPath() );
    }

    @Override
    public Iterator<String> charIterator() {
        return new CharIterator(this);
    }

    @Override
    public Iterator<String> wordIterator() {
        return new WordIterator(this);
    }

    public String getFilename() {
        return filename;
    }

}
