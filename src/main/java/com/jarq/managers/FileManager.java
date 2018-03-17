package com.jarq.managers;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

abstract class FileManager {

    protected <T extends Closeable> void closeCloseable(T t) {
        try {
            if (t != null) {
                t.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void prepareFile(String filePath){
        if(! isFileExist(filePath)){
            createNewFile(filePath);
        }
    }

    private Boolean isFileExist(String filePath){
        File f = new File(filePath);
        return f.exists();
    }

    private void createNewFile(String filePath){
        try {
            File f = new File(filePath);
            f.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}