package com.jarq.iterators;

import com.jarq.model.FileContent;

import java.io.IOException;

public class CharIterator extends StringIterator {

    private Integer index;
    private final String ONLY_LETTERS_REGEX = "[a-zA-Z]";

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
        String character = null;
        while(hasNext()) {
            character = (String.valueOf(getData().charAt(index)));
            index++;
            if (character.matches(ONLY_LETTERS_REGEX)) {
                break;
            }
        }
        return character;
    }
}
