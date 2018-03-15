package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.*;
import java.util.Iterator;

public abstract class StringIterator implements Iterator<String> {
    
    private String data;

    public StringIterator(FileContent fileContent) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(new File(fileContent.getFilename()));
            bufferedReader = new BufferedReader(fileReader);
            data = bufferedReader.readLine();
            if(data == null) {
                closeReader(bufferedReader);
                closeReader(fileReader);
            }
        } catch (IOException e) {
            data = null;
            closeReader(bufferedReader);
            closeReader(fileReader);
            throw e;
        }
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    protected String getData() {
        return data;
    }

    private <T extends Closeable> void closeReader(T reader) {
        if(reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader = null;
            }
        }
    }
}
