package com.epam.optionaltaskthree;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunnerThree {

    private static final String FILE_INPUT =
            "/home/titan/Desktop/Epam/Solutions/IOSolutions/io-task/outputs/TaskThree.java";
    private static final String FILE_OUTPUT =
            "/home/titan/Desktop/Epam/Solutions/IOSolutions/io-task/outputs/TaskThreeOutput.java";

    public static void main(String[] args) {
        File fileInput = new File(FILE_INPUT);
        String fileCode = readCode(fileInput);
        String fileCodeInReverseOrder = reverseCharsOfContent(fileCode);
        File fileOutput = new File(FILE_OUTPUT);
        writeCodeToFile(fileOutput, fileCodeInReverseOrder);
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

    private static String reverseCharsOfContent(String stringContent) {
        StringBuilder reversedStringContent = new StringBuilder();
        Scanner scanner = new Scanner(stringContent);
        String lineOfContent;
        while (scanner.hasNextLine()) {
            lineOfContent = scanner.nextLine();
            reversedStringContent.append(reverseString(lineOfContent));
            reversedStringContent.append('\n');
        }
        scanner.close();
        return reversedStringContent.toString();
    }

    private static String reverseString(String stringToReverse) {
        StringBuilder reversedString = new StringBuilder();
        for (int i = stringToReverse.length() - 1; i >= 0; i--) {
            reversedString.append(stringToReverse.charAt(i));
        }
        return reversedString.toString();
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
