package com.epam.optionaltaskone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RunnerOne {

    private static final String FILE_NAME =
            "/home/titan/Desktop/Epam/Solutions/IOSolutions/io-task/outputs/TaskOne.txt";
    private static final int NUMBER_OF_INTEGERS_TO_WRITE = 100;

    public static void main(String[] args) {
        File file = createFile();
        List<Integer> randomIntegers = generateRandomIntegers(file);
        writeIntegersToFile(file, randomIntegers);
        List<String> integersAsStrings = readIntegersFromFile(file);
        List<Integer> integers = convertStringsToIntegers(integersAsStrings);
        Collections.sort(integers);
        writeIntegersToFile(file, integers);
    }

    private static File createFile() {
        File file = new File(FILE_NAME);
        try {
            boolean createdStatus = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static List<Integer> generateRandomIntegers(File file) {
        List<Integer> randomIntegers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_INTEGERS_TO_WRITE; i++) {
            randomIntegers.add(rand.nextInt());
        }
        return randomIntegers;
    }

    private static List<String> readIntegersFromFile(File file) {
        List<String> integersAsStrings = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                integersAsStrings.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integersAsStrings;
    }

    private static List<Integer> convertStringsToIntegers(List<String> integersAsStrings) {
        List<Integer> integers = new ArrayList<>();
        for (String integerAsString : integersAsStrings) {
            integers.add(Integer.parseInt(integerAsString));
        }
        return integers;
    }

    private static void writeIntegersToFile(File file, List<Integer> integers) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int integer : integers) {
                fileWriter.write(String.valueOf(integer));
                fileWriter.write('\n');
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
