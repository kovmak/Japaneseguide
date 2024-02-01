package com.krnelx.Japaneseguide.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Search class provides methods for searching for a specified term in one or more files.
 */
public class Search {

    /**
     * Searches for the specified term in the given files and prints the matching lines.
     *
     * @param searchTerm The term to search for.
     * @param filePaths  The paths of the files to search in.
     */
    public static void searchInFiles(String searchTerm, String... filePaths) {
        for (String filePath : filePaths) {
            List<String> matchingLines = searchInFile(searchTerm, filePath);
            if (!matchingLines.isEmpty()) {
                System.out.println("\nРезультати пошуку у файлi:" + filePath);
                for (String line : matchingLines) {
                    System.out.println(line);
                }
            }
        }
    }

    /**
     * Searches for the specified term in the given file and returns the matching lines.
     *
     * @param searchTerm The term to search for.
     * @param filePath   The path of the file to search in.
     * @return A list of lines containing the specified term.
     */
    private static List<String> searchInFile(String searchTerm, String filePath) {
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    matchingLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingLines;
    }
}
