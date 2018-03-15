package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class StringIterator implements Iterator<String> {

    private String data;
    private BufferedReader bufferedReader;

    public StringIterator(FileContent fileContent) throws IOException {
        FileReader fileReader = null;
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
        return data != null;
    }

    @Override
    public String next() throws NoSuchElementException {
        String output = data;
        try {
            if(data == null) {
                throw new NoSuchElementException("Next line is not available");
            } else {
                data = bufferedReader.readLine();
                if(data == null) {
                    closeReader(bufferedReader);
                }
            }
        }
        catch(Exception ex) {
            throw new NoSuchElementException("Problem occurred while getting next element");
        }
        return output;
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
            }
        }
    }
}
