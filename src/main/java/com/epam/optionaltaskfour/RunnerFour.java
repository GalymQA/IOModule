package com.epam.optionaltaskfour;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunnerFour {

    private static final String FILE_NAME =
            "/home/titan/Desktop/Epam/Solutions/IOSolutions/io-task/outputs/task_four.txt";
    private static final String REGEX_SPACE = "\s";
    private static final int LENGTH_OF_WORD_TO_REPLACE = 2;

    public static void main(String[] args) {
        File file = new File(FILE_NAME);
        String fileCode = readCode(file);
        String processedFileCode = upperCaseWords(fileCode);
        writeCodeToFile(file, processedFileCode);
    }

    private static String readCode(File file) {
        StringBuilder fileCode = new StringBuilder();
        try {
            Scanner fileReader = new Scanner(new FileReader(file));
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                fileCode.append(line);
                fileCode.append('\n');
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileCode.toString();
    }

    private static String upperCaseWords(String fileContent) {
        String[] splitContent = fileContent.split(REGEX_SPACE);
        StringBuilder processedFileContent = new StringBuilder();
        for (String str : splitContent) {
            if (str.length() > LENGTH_OF_WORD_TO_REPLACE) {
                processedFileContent.append(str.toUpperCase());
                processedFileContent.append("\s");
            } else {
                processedFileContent.append(str);
                processedFileContent.append("\s");
            }
        }
        return processedFileContent.toString();
    }

    private static void writeCodeToFile(File file, String fileCode) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(fileCode);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
