package com.epam.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

    private static final String REPORT_FILE_NAME = "report.txt";
    private static FileWriter reportFileWriter;

    static {
        try {
            reportFileWriter = new FileWriter(REPORT_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final BufferedWriter bufferedWriter = new BufferedWriter(reportFileWriter);

    public static void main(String[] args) throws IOException {
        String inputString = getInputFromConsole();
        File fileInput = new File(inputString);
        int indent = 0;
        writeReportToFile(fileInput, indent);
        bufferedWriter.close();
    }

    public static String getInputFromConsole() {
        System.out.println("Please enter a directory or a file:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void writeReportToFile(File file, int indent) throws IOException {
        if (file.isDirectory()) {
            writeToReportFolderName(file, indent);
            File[] innerFiles = file.listFiles();
            List<File> groupedListOfFoldersAndFiles = getGroupedListOfFoldersAndFiles(innerFiles);
            for (File currentFile : groupedListOfFoldersAndFiles) {
                writeReportToFile(currentFile, indent + 1);
            }
        } else {
            bufferedWriter.write(getIndentStringForNonFolders(indent) + file.getName());
            bufferedWriter.newLine();
        }
    }

    public static void writeToReportFolderName(File file, int indent) throws IOException {
        bufferedWriter.write(getIndentStringForFolders(indent) + "|----- " + file.getName());
        bufferedWriter.newLine();
    }

    private static String getIndentStringForFolders(int indent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            stringBuilder.append("       ");
        }
        return stringBuilder.toString();
    }

    private static List<File> getGroupedListOfFoldersAndFiles(File[] innerFiles) {
        List<File> folders = new ArrayList<>();
        List<File> nonFolders = new ArrayList<>();
        assert innerFiles != null;
        for (File innerFile : innerFiles) {
            if (innerFile.isDirectory()) {
                folders.add(innerFile);
            } else {
                nonFolders.add(innerFile);
            }
        }
        List<File> groupedListOfFoldersAndFiles = new ArrayList<>();
        groupedListOfFoldersAndFiles.addAll(folders);
        groupedListOfFoldersAndFiles.addAll(nonFolders);
        return groupedListOfFoldersAndFiles;
    }

    private static String getIndentStringForNonFolders(int indent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            if (i < indent - 1) {
                stringBuilder.append("       ");
            } else {
                stringBuilder.append("|      ");
            }
        }
        return stringBuilder.toString();
    }

}
