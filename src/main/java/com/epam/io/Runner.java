package com.epam.io;

import java.io.File;
import java.util.Scanner;

public class Runner {

    private static final String REPORT_FILE_NAME = "report.txt";

    public static void main(String[] args) {
//        String inputString = getInputFromConsole();
//        File fileInput = new File(inputString);
//        if (fileInput.isDirectory()) {
//            FolderStructureProcessor folderStructureProcessor = new FolderStructureProcessor(REPORT_FILE_NAME);
//            folderStructureProcessor.writeFolderStructureAndCloseStream(fileInput);
//        } else {
//            ReportReader reportReader = new ReportReader(fileInput);
//            reportReader.getStatisticsData();
//            // close stream
//        }
        String inputString = "report.txt";
        File fileInput = new File(inputString);
        ReportReader reportReader = new ReportReader(fileInput);
        reportReader.printToConsoleDataOnFolderFileStructure();
    }

    public static String getInputFromConsole() {
        System.out.println("Please enter a directory to process or a file to get statistics:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
