package controller;

import model.Language;
import model.Time;
import utilities.LanguageLoader;
import view.FooterPanel;
import view.GamePanel;
import view.TopPanel;
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
    private TopPanel topPanel;
    private GamePanel gamePanel;
    private FooterPanel footerPanel;

    public Controller(Time time,
                      View view) {

        this.availableLanguages = LanguageLoader.loadLanguages(DICTIONARY_PATH);
        this.timeOptions = time.getTimeOptions();

        this.time = time;
        this.view = view;

        this.topPanel = view.getTopPanel();
        this.gamePanel = view.getGamePanel();
        this.footerPanel = view.getFooterPanel();

        addListeners();
        updateView();
    }

    private void addListeners() {
        topPanel.getLanguageListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedLanguage = availableLanguages.stream()
                            .filter(language -> language.getName().equals(newValue))
                            .findFirst()
                            .orElse(null);
                    generateParagraph();
                }
        );

        topPanel.getTimeListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    time.setTimeOptions(timeOptions);
                    time.setDefaultTime(newValue);
//                    footerPanel.updateTime(time.getDefaultTime());
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
        gamePanel.getTypingArea().setText(paragraph.toString());

    }

    private void updateView() {
        List<String> languageNames = this.availableLanguages.stream().map(Language::getName).toList();

        topPanel.updateLists(languageNames, time.getTimeOptions());
        topPanel.getTimeListView().getSelectionModel().select(time.getDefaultTime());
    }


}
