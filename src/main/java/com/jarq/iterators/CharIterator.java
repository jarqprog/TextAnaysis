package com.jarq.iterators;

import com.jarq.enums.RegExpression;
import com.jarq.model.FileContent;

import java.io.IOException;

public class CharIterator extends StringIterator {

    private Integer index;

    public CharIterator(FileContent fileContent) throws IOException {
        super(fileContent);
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < getData().length();
    }

    @Override
    public String next() {
        String regex = RegExpression.ONLY_LETTER.getRegex();
        String character = "";
        while(hasNext()) {
            character = (String.valueOf(getData().charAt(index)));
            index++;
            if (character.matches(regex)) {
                break;
            }
        }
        return character;
    }
}
