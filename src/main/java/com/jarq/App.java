package com.jarq;

import com.jarq.iterators.CharIterator;

import java.io.IOException;
import java.util.Iterator;

public class App {
    public static void main( String[] args ) throws IOException {

        IterableText<String> fileContent = new FileContent("test.txt");
        Iterator<String> charIterator = fileContent.charIterator();

        if(charIterator != null) {
            while (charIterator.hasNext()) {
                String line = charIterator.next();
                System.out.println(line);
            }
        }

        System.out.println( "Hello World!" );
    }
}
