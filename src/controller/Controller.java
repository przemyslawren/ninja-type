package controller;

import model.Language;
import model.Time;
import utilities.LanguageLoader;
import view.View;

import java.util.List;
import java.util.Random;

public class Controller {
    private static final String DICTIONARY_PATH = "./dictionary";
    private List<Language> availableLanguages;
    private Language selectedLanguage;
    private List<String> timeOptions;
    private Time time;
    private View view;

    public Controller(Time time, View view) {

        this.availableLanguages = LanguageLoader.loadLanguages(DICTIONARY_PATH);
        this.timeOptions = time.getTimeOptions();

        this.time = time;
        this.view = view;

        addListeners();
        updateView();
    }

    private void addListeners() {
        view.getTopPanel().getLanguageListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedLanguage = availableLanguages.stream()
                            .filter(language -> language.getName().equals(newValue))
                            .findFirst()
                            .orElse(null);
                    generateParagraph();

                    System.out.println("Selected language: " + selectedLanguage.getName());
                    System.out.println("Generated paragraph: " + view.getGamePanel().getDisplayArea().getText());
                }
        );

        view.getTopPanel().getTimeListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    time.setTimeOptions(timeOptions);
                    time.setDefaultTime(newValue);

                    System.out.println("Selected time: " + time.getDefaultTime());
                }
        );
    }

    private void generateParagraph() {
        Random random = new Random();
        List<String> words = selectedLanguage.getDictionary();
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            paragraph.append(words.get(random.nextInt(words.size()))).append(" ");
        }
        view.getGamePanel().getDisplayArea().setText(paragraph.toString());

    }

    private void updateView() {
        List<String> languageNames = this.availableLanguages.stream().map(Language::getName).toList();

        view.getTopPanel().updateLists(languageNames, time.getTimeOptions());
        view.getTopPanel().getTimeListView().getSelectionModel().select(time.getDefaultTime());
    }


}
