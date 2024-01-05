import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private static final String DICTIONARY_PATH = "./dictionary";
    private List<String> timeOptions;

    public Model() {
        timeOptions = new ArrayList<>(Arrays.asList("15", "30", "45", "60","90","120","300"));
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

    private String convertToTitleCase(String substring) {
        if (substring == null || substring.isEmpty()) {
            return substring;
        }
        return substring.substring(0, 1).toUpperCase() + substring.substring(1).toLowerCase();
    }

    public List<String> getTimeOptions() {
        return timeOptions;
    }
}
