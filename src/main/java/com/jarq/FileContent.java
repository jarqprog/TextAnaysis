package com.jarq;

import com.jarq.iterators.CharIteroator;
import com.jarq.iterators.WordIterator;

import java.io.*;
import java.util.Iterator;

public class FileContent implements IterableText<String> {

    private String filename;
    private File file;

    public FileContent(String filename) throws IOException {
        this(new File(filename));
        this.filename = filename;
    }

    private FileContent(File file) throws IOException {
        if( ! file.exists() )
            throw new FileNotFoundException( "File doesn't exist: " + file.getPath() );
        if( ! file.isFile() )
            throw new IOException( "File is not of type 'file': " + file.getPath() );
        this.file = file;
    }

    @Override
    public Iterator<String> charIterator() {
        return new CharIteroator(this);
    }

    @Override
    public Iterator<String> wordIterator() {
        return new WordIterator(this);
    }

    public String getFilename() {
        return filename;
    }

}
