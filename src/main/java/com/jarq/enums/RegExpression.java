package com.jarq.enums;

public enum RegExpression {

    ONLY_LETTER("[a-zA-Z]");

    private String regex;

    RegExpression(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
