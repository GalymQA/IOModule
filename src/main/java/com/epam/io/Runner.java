package com.epam.io;

import java.io.IOException;

public class Runner {

    private static final String FILE_TO_WRITE = "folder_structure_report.txt";

    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        arguments.verifyArguments();
        String argument = arguments.getArgument();
        FileInput fileInput = new FileInput(argument);
        fileInput.verifyExistenceOfFileOrFolder();
        if (fileInput.isDirectory()) {
            FolderAnalyzer folderAnalyzer = new FolderAnalyzer(fileInput, FILE_TO_WRITE);
            folderAnalyzer.writeFolderStructureToFileAndCloseStream();
        } else {
            ReportAnalyzer reportAnalyzer = new ReportAnalyzer(fileInput);
            reportAnalyzer.printToConsoleDataOnFolderFileStructure();
        }
    }

}
