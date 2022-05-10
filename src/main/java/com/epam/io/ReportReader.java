package com.epam.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReportReader {

    private static final String REGEX_NEW_LINE = "\n";
    private static final String REGEX_FOR_FOLDER = "|----- ";
    private static final String REGEX_FOR_FILE = "|      ";
    private static final String REGEX_FOR_FILE_ENCODED = "\\|\\s\\s\\s\\s\\s\\s"; // Stands for "|      "

    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public ReportReader(File fileInput) {
        try {
            fileReader = new FileReader(fileInput);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printToConsoleDataOnFolderFileStructure() {
        String contentOfReport = convertContentOfReportToString();
        System.out.println(contentOfReport);
        int countOfFolders = getCountOfFolders(contentOfReport);
        System.out.println("The number of folders : " + countOfFolders);
        int countOfFiles = getCountOfFiles(contentOfReport);
        System.out.println("The number of files : " + countOfFiles);
        double averageCountOfFiles = getAverageCountOfFilesInFolder(countOfFolders, countOfFiles);
        System.out.println("The average number of files in folders : " + averageCountOfFiles);
        double averageLengthOfFiles = getAverageLengthOfFiles(contentOfReport);
        System.out.println("The average length of files : " + averageLengthOfFiles);
    }

    public String convertContentOfReportToString() {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(REGEX_NEW_LINE);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public int getCountOfFolders(String contentOfReportAsString) {
        int countOfFolders = 0;
        String[] contentAsArray = contentOfReportAsString.split(REGEX_NEW_LINE);
        for (String row : contentAsArray) {
            if (row.contains(REGEX_FOR_FOLDER)) {
                countOfFolders++;
            }
        }
        return countOfFolders;
    }

    public int getCountOfFiles(String contentOfReportAsString) {
        return getOnlyFileNames(contentOfReportAsString).size();
    }

    public double getAverageCountOfFilesInFolder(int countOfFolders, int countOfFiles) {
        if (countOfFolders == 0) {
            return countOfFiles * 1.0;
        }
        return ((countOfFiles * 1.0) / countOfFolders);
    }

    public double getAverageLengthOfFiles(String stringBuffer) {
        List<String> onlyFileNames = getOnlyFileNames(stringBuffer);
        if (onlyFileNames.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (String fileName : onlyFileNames) {
            sum += fileName.length();
        }
        return ((sum * 1.0) / onlyFileNames.size());
    }

    public List<String> getOnlyFileNames(String contentOfReport) {
        String[] contentAsArray = contentOfReport.split(REGEX_NEW_LINE);
        List<String> onlyFileNames = new ArrayList<>();
        for (String element : contentAsArray) {
            if (element.contains(REGEX_FOR_FILE)) {
                String onlyFileName = splitStringByRegexAndGetFirstPart(element);
                onlyFileNames.add(onlyFileName);
            }
        }
        return onlyFileNames;
    }

    public String splitStringByRegexAndGetFirstPart(String string) {
        return string.split(REGEX_FOR_FILE_ENCODED)[1];
    }

}
