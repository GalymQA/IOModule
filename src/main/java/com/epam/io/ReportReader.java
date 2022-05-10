package com.epam.io;

import java.io.*;

public class ReportReader {

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

    public void getStatisticsData() {
        StringBuffer contentOfReport = getContentOfReport();
        System.out.println(contentOfReport.toString());
        int countOfFolders = getCountOfFolders(contentOfReport);
        System.out.println("The number of folders : " + countOfFolders);
        int countOfFiles = getCountOfFiles(contentOfReport);
        System.out.println("The number of files : " + countOfFiles);
        Double averageCountOfFiles = getAverageCountOfFilesInFolder(countOfFolders, countOfFiles);
        System.out.println("The average number of files in folders : " + averageCountOfFiles);
    }

    public StringBuffer getContentOfReport() {
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public int getCountOfFolders(StringBuffer stringBuffer) {
        int countOfFolders = 0;
        String[] contentAsArray = stringBuffer.toString().split("\n");
        for (String str : contentAsArray) {
            if (str.contains("|----- ")) {
                countOfFolders++;
            }
        }
        return countOfFolders;
    }

    public int getCountOfFiles(StringBuffer stringBuffer) {
        int countOfFiles = 0;
        String[] contentAsArray = stringBuffer.toString().split("\n");
        for (String str : contentAsArray) {
            if (str.contains("|      ")) {
                countOfFiles++;
            }
        }
        return countOfFiles;
    }

    public double getAverageCountOfFilesInFolder(int countOfFolders, int countOfFiles) {
        if (countOfFolders == 0) {
            return countOfFiles * 1.0;
        }
        return ((countOfFiles * 1.0) / countOfFolders);
    }
}
