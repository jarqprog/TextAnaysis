package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        String characterMatcher = "[a-zA-Z]";
        String character = "";
        Pattern regex = Pattern.compile(characterMatcher);
        while(hasNext()) {
            character = String.valueOf(getData().charAt(index));
            Matcher matcher = regex.matcher(character);
            index++;
            if (matcher.find()) {
                break;
            }
        }
        return character;
    }
}
