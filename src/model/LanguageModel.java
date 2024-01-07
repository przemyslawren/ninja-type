package model;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LanguageModel {
    private static final String DICTIONARY_PATH = "./dictionary";

    public LanguageModel() {
    }

    public List<String> getAvailableLanguages() {
        File folder = new File(DICTIONARY_PATH);
        File[] listOfFiles = folder.listFiles();
        List<String> languages = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        String languageName = convertToTitleCase(fileName.substring(0, fileName.length() - 4));
                        languages.add(languageName);
                    }
                }
            }
        }

        return languages;
    }


    public List<String> getRandomWords(String language, int wordCount) {
        List<String> words = new ArrayList<>();
        Path filePath = Path.of(DICTIONARY_PATH, language + ".txt");

        try {
            List<String> allWords = java.nio.file.Files.readAllLines(filePath);
            Random random = new Random();
            for (int i = 0; i < wordCount; i++) {
                words.add(allWords.get(random.nextInt(allWords.size())));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return words;
    }

    private String convertToTitleCase(String substring) {
        if (substring == null || substring.isEmpty()) {
            return substring;
        }
        return substring.substring(0, 1).toUpperCase() + substring.substring(1).toLowerCase();
    }

}
