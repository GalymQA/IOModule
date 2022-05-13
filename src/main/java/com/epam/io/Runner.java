package com.epam.io;

import com.epam.io.analyzer.Arguments;
import com.epam.io.analyzer.FileInput;
import com.epam.io.analyzer.FolderAnalyzer;
import com.epam.io.analyzer.ReportAnalyzer;
import com.epam.io.utilities.PropertyLoader;

import java.io.IOException;

public class Runner {

    private static final String FILE_TO_WRITE;
    
    static {
        FILE_TO_WRITE = PropertyLoader.getProperty("FILE_TO_WRITE");
    }

    /**
     * A runner that either analyzes a folder's tree structure or analyzes the generated report.
     * @param args should contain only one file/folder input
     */
    public static void main(String[] args) throws IOException {
        Arguments arguments = new Arguments(args);
        arguments.verifyArguments();
        String argument = arguments.getArgument();
        FileInput fileInput = new FileInput(argument);
        fileInput.verifyExistenceOfFileOrFolder();
        if (fileInput.isDirectory()) {
            FolderAnalyzer folderAnalyzer = new FolderAnalyzer(fileInput.getFile(), FILE_TO_WRITE);
            folderAnalyzer.writeFolderStructureToFileAndCloseStream();
        } else {
            ReportAnalyzer reportAnalyzer = new ReportAnalyzer(fileInput.getFile());
            reportAnalyzer.printToConsoleStatisticsOnReport();
        }
    }

}
