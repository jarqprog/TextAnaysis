package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String characterMatcher = "[a-zA-Z]";
        Pattern regex = Pattern.compile(characterMatcher);
        StringBuilder sb = new StringBuilder();
        while(hasNext()) {
            String character = String.valueOf(getData().charAt(index));
            Matcher matcher = regex.matcher(character);
            index++;
            if (matcher.find()) {
                sb.append(character);
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
