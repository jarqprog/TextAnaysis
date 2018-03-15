package com.jarq;


import java.io.IOException;
import java.util.Iterator;

public class App {
    public static void main( String[] args ) {

        try {
//            IterableText<String> fileContent = new FileContent("test.txt");
//            Iterator<String> charIterator = fileContent.charIterator();
//
//            if(charIterator != null) {
//                while (charIterator.hasNext()) {
//                    String character = charIterator.next();
//                    System.out.println(character);
//                }
//            }

            IterableText<String> fileContent1 = new FileContent("test2.txt");
            Iterator<String> wordIt = fileContent1.wordIterator();

            if(wordIt != null) {
                while (wordIt.hasNext()) {
                    String word = wordIt.next();
                    System.out.println(word);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        System.out.println( "Hello World!" );
    }
}
