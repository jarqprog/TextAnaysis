package com.jarq.iterators;

import com.jarq.enums.RegExpression;
import com.jarq.model.FileContent;

import java.util.Iterator;

public class WordIterator implements Iterator<String> {

    private Integer index;
    private String text;

    public WordIterator(FileContent fileContent) {
        text = fileContent.getText();
        index = 0;
    }

    public boolean hasNext() {
        return index < text.length();
    }

    public String next() {
        String regex = RegExpression.ONLY_LETTER.getRegex();
        StringBuilder sb = new StringBuilder();
        while(hasNext()) {
            String character = String.valueOf(text.charAt(index));
            index++;
            if (character.matches(regex)) {
                sb.append(character);
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
