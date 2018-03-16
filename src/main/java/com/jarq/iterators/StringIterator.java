package com.jarq.iterators;

import com.jarq.model.FileContent;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class StringIterator implements Iterator<String> {

    private String data;

    public StringIterator(FileContent fileContent) throws IOException {
        data = "";
        setupData(fileContent);
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

    private void setupData(FileContent fileContent) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line;
        String nextLine = "\n";
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(new File(fileContent.getFilename()));
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\\s+"," ");
                if(line.length() > 0) {
                    sb.append(line.trim());
                    sb.append(nextLine);
                }
            }
            data = sb.toString();
            removeNonLetterElementsFromEndOfDataCollection();
            if(data.length() == 0) {
                throw new NoSuchElementException("Object must be recreated!");
            }
        } catch (IOException e) {
            data = null;
            throw e;
        } finally {
            closeReader(bufferedReader);
            closeReader(fileReader);
        }
    }

    private void removeNonLetterElementsFromEndOfDataCollection() {
        String ONLY_LETTERS_REGEX = "[a-zA-Z]";
        for(int i=data.length()-1; i >=0; i--) {
            if(String.valueOf(data.charAt(i)).matches(ONLY_LETTERS_REGEX)) {
                break;
            }
            data = data.substring(0, i);
        }
    }
}
