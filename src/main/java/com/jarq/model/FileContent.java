package com.jarq.model;

import com.jarq.enums.RegExpression;
import com.jarq.iterators.CharIterator;
import com.jarq.iterators.WordIterator;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileContent implements IterableText<String> {

    private final String filename;
    private String text;

    public FileContent(String filename) throws IOException {
        checkFile(new File(filename));
        this.filename = filename;
        text = createText();
    }

    @Override
    public Iterator<String> charIterator() {
        return new CharIterator(this);
    }

    @Override
    public Iterator<String> wordIterator() {
        return new WordIterator(this);
    }

    @Override
    public String getText() {
        return text;
    }

    public String getFilename() {
        return filename;
    }

    private void checkFile(File file) throws IOException {
        if(!file.exists())
            throw new FileNotFoundException("File doesn't exist: " + file.getPath());
        if(!file.isFile())
            throw new IOException("Given file is not of type 'file': " + file.getPath());
        if(file.length() == 0)
            throw new IOException("Given file empty: " + file.getPath());
    }

    private String createText() throws IOException {
        String line;
        String nextLine = "\n";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)))) {
            while((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\\s+"," ");
                if(line.length() > 0) {
                    sb.append(line.trim());
                    sb.append(nextLine);
                }
            }
            text = sb.toString();
            removeNonLetterElementsFromEndOfText();
            if(text.length() == 0) {
                throw new NoSuchElementException("There is no text data in file! Change file.");
            }
        } catch (IOException e) {
            throw new IOException();
        }
        return text;
    }

    private void removeNonLetterElementsFromEndOfText() {
        String regex = RegExpression.ONLY_LETTER.getRegex();
        int cutIndex = text.length()-1;
        for(int i=cutIndex; i >=0; i--) {
            if (String.valueOf(text.charAt(i)).matches(regex)) {
                break;
            }
            cutIndex = i;
        }
        text = text.substring(0, cutIndex);
    }
}
