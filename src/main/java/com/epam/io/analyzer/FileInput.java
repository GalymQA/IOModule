package com.epam.io.analyzer;

import java.io.File;
import java.io.IOException;

public class FileInput {

    private File file;

    public FileInput(String argument) {
        file = new File(argument);
    }

    public void verifyExistenceOfFileOrFolder() throws IOException {
        if (!file.exists()) {
            throw new IOException("It seems that the entered file does not exist.");
        }
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
