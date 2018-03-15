package com.jarq.iterators;

import com.jarq.FileContent;

import java.io.*;
import java.util.Iterator;

public abstract class StringIterator implements Iterator<String> {

    private String data;

    public StringIterator(FileContent fileContent) throws IOException {
        data = "";
        setupData(fileContent);
    }

    protected String getData() {
        return data;
    }

    private <T extends Closeable> void closeReader(T reader) {
        if(reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupData(FileContent fileContent) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line;
        String nextLine = "\n";
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(new File(fileContent.getFilename()));
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\\s+"," ");
                if(line.length() > 0) {
                    sb.append(line.trim());
                    sb.append(nextLine);
                }
            }
            data = sb.toString();
        } catch (IOException e) {
            data = null;
            throw e;
        } finally {
            closeReader(bufferedReader);
            closeReader(fileReader);
        }
    }
}
