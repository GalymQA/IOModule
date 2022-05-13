package com.epam.io.analyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderAnalyzer {

    private static final String PREFIX_FOR_FOLDERS = "|----- ";
    private static final String PREFIX_FOR_SUB_ELEMENT = "       ";
    private static final String PREFIX_FOR_NON_FOLDERS = "|      ";

    private final File file;
    private final String reportFileName;
    private final BufferedWriter bufferedWriter;

    public FolderAnalyzer(File file, String reportFileName) throws IOException {
        this.file = file;
        this.reportFileName = reportFileName;
        this.bufferedWriter = new BufferedWriter(new FileWriter(reportFileName));
    }

    public void writeFolderStructureToFileAndCloseStream() {
        int indent = 0;
        try {
            writeReportToFile(file, indent);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeReportToFile(File file, int indent) throws IOException {
        if (file.isDirectory()) {
            writeFolderNameToReport(file, indent);
            File[] innerFiles = file.listFiles();
            assert innerFiles != null;
            List<File> groupedListOfFoldersAndFiles = getGroupedListOfFoldersAndFiles(innerFiles);
            for (File currentFile : groupedListOfFoldersAndFiles) {
                writeReportToFile(currentFile, indent + 1);
            }
        } else {
            writeFileNameToReport(file, indent);
        }
    }

    private void writeFolderNameToReport(File file, int indent) throws IOException {
        if (indent == 0) {
            bufferedWriter.write(file.getName());
            bufferedWriter.newLine();
        } else {
            bufferedWriter.write(getIndentStringForFolders(indent) + PREFIX_FOR_FOLDERS + file.getName());
            bufferedWriter.newLine();
        }
    }

    private void writeFileNameToReport(File file, int indent) throws IOException {
        bufferedWriter.write(getIndentStringForNonFolders(indent + 1) + file.getName());
        bufferedWriter.newLine();
    }

    private String getIndentStringForFolders(int indent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            stringBuilder.append(PREFIX_FOR_SUB_ELEMENT);
        }
        return stringBuilder.toString();
    }

    private List<File> getGroupedListOfFoldersAndFiles(File[] innerFiles) {
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

    private String getIndentStringForNonFolders(int indent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            if (i < indent - 1) {
                stringBuilder.append(PREFIX_FOR_SUB_ELEMENT);
            } else {
                stringBuilder.append(PREFIX_FOR_NON_FOLDERS);
            }
        }
        return stringBuilder.toString();
    }

    public File getFile() {
        return file;
    }

    public String getReportFileName() {
        return reportFileName;
    }

}
