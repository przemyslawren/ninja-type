
import javafx.scene.control.TextField;

import java.util.List;

public class Controller {
    private Model model;
    private View view;
    private TextField typingArea;

    public Controller(Model model, View view, TextField typingArea) {
        this.model = model;
        this.view = view;
        this.typingArea = typingArea;
        updateView();

        // Przykładowa logika reagująca na zmianę języka

    }

    private void updateTypingArea(String language) {
        List<String> words = model.getRandomWords(language, 30);
        typingArea.setText(String.join(" ", words));
    }

    private void updateView() {
        List<String> languages = model.getAvailableLanguages();
        List<String> timeOptions = model.getTimeOptions();

        view.updateLists(languages, timeOptions);
        view.getLanguageListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateTypingArea(newValue)
        );
    }

}
