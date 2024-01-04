import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private static final String DICTIONARY_PATH = "./dictionary";
    private List<String> timeOptions;

    public Model() {
        timeOptions = new ArrayList<>(Arrays.asList("15 sekund", "30 sekund", "1 minuta", "2 minuty"));
    }

    public List<String> getAvailableLanguages() {
        File folder = new File(DICTIONARY_PATH);
        File[] listOfFiles = folder.listFiles();
        List<String> languages = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    languages.add(file.getName());
                }
            }
        }

        return languages;
    }

    public List<String> getTimeOptions() {
        return timeOptions;
    }
}
