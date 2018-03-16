package com.jarq.iterators;

import com.jarq.enums.RegExpression;
import com.jarq.model.FileContent;

import java.io.IOException;

public class WordIterator extends StringIterator {

    private Integer index;

    public WordIterator(FileContent fileContent) throws IOException {
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
        StringBuilder sb = new StringBuilder();
        while(hasNext()) {
            String character = String.valueOf(getData().charAt(index));
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
