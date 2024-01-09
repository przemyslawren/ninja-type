package utilities;

import model.Language;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LanguageLoader {

    public static List<Language> loadLanguages(String DICTIONARY_PATH) {
        List<Language> languages = new ArrayList<>();
        File folder = new File(DICTIONARY_PATH);
        File[] listOfFiles = folder.listFiles();

        try {
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        String name = file.getName();
                        try {
                            List<String> dictionary = Files.readAllLines(Path.of(file.getPath()));
                            languages.add(new Language(name, dictionary));
                        } catch (Exception e) {
                            System.out.println("Error reading file: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading directory: " + e.getMessage());
        }
        return languages;
    }

}
