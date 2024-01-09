package controller;

import javafx.application.Platform;
import model.Language;
import model.Time;
import utilities.GameTimer;
import utilities.LanguageLoader;
import view.View;

import java.util.List;
import java.util.Random;

public class Controller {
    private static final String DICTIONARY_PATH = "./dictionary";
    private List<Language> availableLanguages;
    private Language selectedLanguage;
    private List<String> timeOptions;
    private GameTimer gameTimer;
    private Time time;
    private View view;

    public Controller(Time time, View view) {

        this.availableLanguages = LanguageLoader.loadLanguages(DICTIONARY_PATH);
        this.timeOptions = time.getTimeOptions();

        this.time = time;
        this.view = view;

        addListeners();
        addActions();
        updateView();
        view.toggleInstructionPanel(true);
    }

    private void addListeners() {
        view.getTopPanel().getLanguageListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedLanguage = availableLanguages.stream()
                            .filter(language -> language.getName().equals(newValue))
                            .findFirst()
                            .orElse(null);
                    System.out.println("Selected language: " + selectedLanguage.getName());
                }
        );

        view.getTopPanel().getTimeListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    time.setTimeOptions(timeOptions);
                    time.setCurrentTime(newValue);
                    view.getFooterPanel().getTimerLabel().setText(formatTime(Integer.parseInt(time.getCurrentTime())));

                    System.out.println("Selected time: " + time.getCurrentTime());
                }
        );
    }

    private void addActions() {
        view.getInstructionPanel().getStartButton().setOnAction(
                event -> {
                    view.toggleInstructionPanel(false);
                    startGame();
                }
        );
    }

    private void startGame() {
        view.getGamePanel().getInputArea().requestFocus();
        gameTimer = new GameTimer(Integer.parseInt(time.getCurrentTime()), this::updateTimeLabel);
        gameTimer.start();
        generateParagraph();
    }

    private void updateTimeLabel() {
        Platform.runLater(() -> {
            int currentTime = gameTimer.getTime();
            view.getFooterPanel().getTimerLabel().setText(formatTime(currentTime));
            if (currentTime == 0) {
                gameTimer.stopTimer();
//                view.toggleResultPanel(true);
            }
        });
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void generateParagraph() {
        Random random = new Random();
        List<String> words = selectedLanguage.getDictionary();
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            paragraph.append(words.get(random.nextInt(words.size()))).append(" ");
        }
        view.getGamePanel().getDisplayArea().setText(paragraph.toString());
        System.out.println("Generated paragraph: " + paragraph);
    }

    private void updateView() {
        List<String> languageNames = this.availableLanguages.stream().map(Language::getName).toList();

        view.getTopPanel().updateLists(languageNames, time.getTimeOptions());
        view.getTopPanel().getTimeListView().getSelectionModel().select(time.getCurrentTime());
        view.getFooterPanel().getTimerLabel().setText(formatTime(Integer.parseInt(time.getCurrentTime())));
    }


}
