package com.jarq.iterators;

import com.jarq.enums.RegExpression;
import com.jarq.model.FileContent;

import java.util.Iterator;

public class CharIterator implements Iterator<String> {

    private Integer index;
    private String text;

    public CharIterator(FileContent fileContent) {
        text = fileContent.getText();
        index = 0;
    }

    public boolean hasNext() {
        return index < text.length();
    }

    public String next() {
        String regex = RegExpression.ONLY_LETTER.getRegex();
        String character = "";
        while(hasNext()) {
            character = (String.valueOf(text.charAt(index)));
            index++;
            if (character.matches(regex)) {
                break;
            }
        }
        return character;
    }
}
