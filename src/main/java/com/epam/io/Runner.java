package com.epam.io;

import java.io.File;
import java.util.Scanner;

public class Runner {

    private static final String FILE_NAME_FOR_FILE_STRUCTURE = "report.txt";

    public static void main(String[] args) {
        String inputString = getInputFromConsole();
        File fileInput = new File(inputString);
        verifyExistenceOfFile(fileInput);
        if (fileInput.isDirectory()) {
            FolderStructureProcessor folderStructureProcessor = new FolderStructureProcessor(FILE_NAME_FOR_FILE_STRUCTURE);
            folderStructureProcessor.writeFolderStructureAndCloseStream(fileInput);
        } else {
            ReportReader reportReader = new ReportReader(fileInput);
            reportReader.printToConsoleDataOnFolderFileStructure();
        }
    }

    private static void verifyExistenceOfFile(File fileInput) {
        if (!fileInput.exists()) {
            System.out.println("Application Error: It seems that the entered file does not exist");
            System.out.println("Stopping the application");
            System.exit(1);
        }
    }

    private static String getInputFromConsole() {
        System.out.println("Please enter a directory to process its file structure or a file to print statistics:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
