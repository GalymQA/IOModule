package com.epam.optionaltasktwo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunnerTwo {

    private static final String FILE_NAME =
            "/home/titan/Desktop/Epam/Solutions/IOSolutions/io-task/outputs/task_two.txt";

    private static final String REGEX_PUBLIC_AT_BEGINNING = "^public\s";
    private static final String REGEX_SPACE_PUBLIC_SPACE = "\spublic\s";
    private static final String REGEX_NEW_LINE_PUBLIC_SPACE = "\npublic\s";
    private static final String REGEX_CURLY_BRACE_PUBLIC_SPACE = "}public\s";
    private static final String REGEX_SEMICOLON_PUBLIC_SPACE = ";public\s";

    private static final String REGEX_PRIVATE_SPACE = "private\s";
    private static final String REGEX_SPACE_PRIVATE_SPACE = "\sprivate\s";
    private static final String REGEX_NEW_LINE_PRIVATE_SPACE = "\nprivate\s";
    private static final String REGEX_CURLY_BRACE_PRIVATE_SPACE = "}private\s";
    private static final String REGEX_SEMICOLON_PRIVATE_SPACE = ";private\s";

    public static void main(String[] args) {
        File file = new File(FILE_NAME);
        String fileCode = readCodeFromFile(file);
        String innerPartOfCode = getInnerCode(fileCode);
        String innerCodeWithReplacement = replace(innerPartOfCode);
        String codeBeforeInnerCode = getCodeBeforeInnerCode(fileCode);
        String codeAfterInnerCode = getCodeAfterInnerCode(fileCode);
        String fileCodeWithReplacements = mergeCode(codeBeforeInnerCode, innerCodeWithReplacement, codeAfterInnerCode);
        writeCodeToFile(file, fileCodeWithReplacements);
    }

    private static String readCodeFromFile(File file) {
        StringBuilder fileCode = new StringBuilder();
        try {
            Scanner fileReader = new Scanner(new FileReader(file));
            fileCode = new StringBuilder();
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

    private static String getInnerCode(String fileCode) {
        int startIndex = fileCode.indexOf("{") + 1;
        int endIndex = fileCode.lastIndexOf("}");
        String innerCode = "";
        if (startIndex > 0 && endIndex > startIndex) {
            innerCode = fileCode.substring(startIndex, endIndex);
        }
        return innerCode;
    }

    private static String getCodeBeforeInnerCode(String fileCode) {
        int startIndex = 0;
        int endIndex = fileCode.indexOf("{") + 1;
        String codeBeforeInnerCode = "";
        if (endIndex > startIndex) {
            codeBeforeInnerCode = fileCode.substring(startIndex, endIndex);
        }
        return codeBeforeInnerCode;
    }

    private static String getCodeAfterInnerCode(String fileCode) {
        int startIndex = fileCode.lastIndexOf("}");
        int endIndex = fileCode.length() - 1;
        String codeAfterInnerCode = "";
        if (endIndex > startIndex) {
            codeAfterInnerCode = fileCode.substring(startIndex, endIndex);
        }
        return codeAfterInnerCode;
    }

    private static String replace(String string) {
        String stringWithReplacements = string;
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(REGEX_PUBLIC_AT_BEGINNING);
        matcher = pattern.matcher(stringWithReplacements);
        stringWithReplacements = matcher.replaceAll(REGEX_PRIVATE_SPACE);
        pattern = Pattern.compile(REGEX_SPACE_PUBLIC_SPACE);
        matcher = pattern.matcher(stringWithReplacements);
        stringWithReplacements = matcher.replaceAll(REGEX_SPACE_PRIVATE_SPACE);
        pattern = Pattern.compile(REGEX_NEW_LINE_PUBLIC_SPACE);
        matcher = pattern.matcher(stringWithReplacements);
        stringWithReplacements = matcher.replaceAll(REGEX_NEW_LINE_PRIVATE_SPACE);
        pattern = Pattern.compile(REGEX_CURLY_BRACE_PUBLIC_SPACE);
        matcher = pattern.matcher(stringWithReplacements);
        stringWithReplacements = matcher.replaceAll(REGEX_CURLY_BRACE_PRIVATE_SPACE);
        pattern = Pattern.compile(REGEX_SEMICOLON_PUBLIC_SPACE);
        matcher = pattern.matcher(stringWithReplacements);
        stringWithReplacements = matcher.replaceAll(REGEX_SEMICOLON_PRIVATE_SPACE);
        return stringWithReplacements;
    }

    public static String mergeCode(String codeBeforeInnerCode, String innerCode, String codeAfterInnerCode) {
        return (codeBeforeInnerCode + innerCode + codeAfterInnerCode);
    }

    public static void writeCodeToFile(File file, String fileCode) {
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
