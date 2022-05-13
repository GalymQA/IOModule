package com.epam.io.analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReportAnalyzer {

    private static final String REGEX_NEW_LINE = "\n";
    private static final String REGEX_FOR_FOLDER = "|----- ";
    private static final String REGEX_FOR_FILE = "|      ";
    private static final String ENCODED_REGEX_FOR_FILE = "\\|\\s\\s\\s\\s\\s\\s"; // Stands for REGEX_FOR_FILE

    private final File file;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    public ReportAnalyzer(File file) throws FileNotFoundException {
        this.file = file;
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
    }

    public void printToConsoleStatisticsOnReport() {
        String contentOfReport = convertContentOfReportToString();
        System.out.println("The statistics on the folder structure: ");
        int countOfFolders = getCountOfFolders(contentOfReport);
        System.out.println(" - The number of folders : " + countOfFolders);
        int countOfFiles = getCountOfFiles(contentOfReport);
        System.out.println(" - The number of files : " + countOfFiles);
        double averageCountOfFiles = getAverageCountOfFilesInFolder(countOfFolders, countOfFiles);
        System.out.println(" - The average number of files in folders : " + String.format("%.2f", averageCountOfFiles));
        double averageLengthOfFiles = getAverageLengthOfFiles(contentOfReport);
        System.out.println(" - The average length of files : " + String.format("%.2f", averageLengthOfFiles));
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

    public int getCountOfFolders(String contentOfReport) {
        int countOfFolders = 0;
        String[] contentAsArray = contentOfReport.split(REGEX_NEW_LINE);
        for (String row : contentAsArray) {
            if (row.contains(REGEX_FOR_FOLDER)) {
                countOfFolders++;
            }
        }
        return countOfFolders;
    }

    public int getCountOfFiles(String contentOfReport) {
        return getOnlyFileNames(contentOfReport).size();
    }

    public double getAverageCountOfFilesInFolder(int countOfFolders, int countOfFiles) {
        if (countOfFolders == 0) {
            return countOfFiles * 1.0;
        }
        return ((countOfFiles * 1.0) / countOfFolders);
    }

    public double getAverageLengthOfFiles(String contentOfReport) {
        List<String> onlyFileNames = getOnlyFileNames(contentOfReport);
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
        return string.split(ENCODED_REGEX_FOR_FILE)[1];
    }

    public File getFile() {
        return file;
    }
}
