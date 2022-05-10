package com.epam.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderStructureProcessor {

    private static final String APPENDIX_FOR_FOLDERS = "|----- ";
    private static final String APPENDIX_FOR_SUB_ELEMENT = "       ";
    private static final String APPENDIX_FOR_NON_FOLDERS = "|      ";

    private BufferedWriter bufferedWriter;

    public FolderStructureProcessor(String reportFileName) {
        try {
            FileWriter reportFileWriter = new FileWriter(reportFileName);
            bufferedWriter = new BufferedWriter(reportFileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFolderStructureAndCloseStream(File folder) {
        int indent = 0;
        try {
            writeReportToFile(folder, indent);
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
            bufferedWriter.write(getIndentStringForFolders(indent) + APPENDIX_FOR_FOLDERS + file.getName());
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
            stringBuilder.append(APPENDIX_FOR_SUB_ELEMENT);
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
                stringBuilder.append(APPENDIX_FOR_SUB_ELEMENT);
            } else {
                stringBuilder.append(APPENDIX_FOR_NON_FOLDERS);
            }
        }
        return stringBuilder.toString();
    }

}
