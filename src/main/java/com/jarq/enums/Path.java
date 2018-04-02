package com.jarq.enums;

public enum Path {

    RESOURCES_DIRECTORY("resources/");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
