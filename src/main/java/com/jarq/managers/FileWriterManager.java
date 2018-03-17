package com.jarq.managers;

import java.io.*;

public class FileWriterManager extends FileManager implements WriterManager {

    private FileWriter fileWriter;
    private BufferedWriter bufWriter;
    private String filePath;

    public static WriterManager getInstance(String filePath) {
        return new FileWriterManager(filePath);
    }

    private FileWriterManager(String filePath) {
        prepareFile(filePath);
        this.filePath = filePath;
    }

    @Override
    public void write(String text, Boolean shouldAppend) {
        try {
            openWriters(shouldAppend);
            bufWriter.write(text);
        } catch (IOException e) {
            System.out.println("problem with saving result to file occurred");
        } finally {
            closeWriters();
        }

    }

    private void openWriters(boolean shouldAppend) throws IOException {
        fileWriter = null;
        bufWriter = null;
        fileWriter = new FileWriter(filePath, shouldAppend);
        bufWriter = new BufferedWriter(fileWriter);
    }

    private void closeWriters() {
        closeCloseable(bufWriter);
        closeCloseable(fileWriter);
    }
}
