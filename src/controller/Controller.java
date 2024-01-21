package controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import model.GameStats;
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
    private GameStats gameStats;
    private Time time;
    private View view;

    public Controller(Time time, View view) {

        this.availableLanguages = LanguageLoader.loadLanguages(DICTIONARY_PATH);
        this.timeOptions = time.getTimeOptions();
        selectedLanguage = availableLanguages.stream()
                .filter(language -> language.getName().equalsIgnoreCase("english"))
                .findFirst()
                .orElse(null);


        this.time = time;
        this.view = view;
        this.gameStats = new GameStats();

        addListeners();
        updateView();
        addShortcuts();
    }

    private void addShortcuts() {

        KeyCombination restartCombination = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
        KeyCombination pauseCombination = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        KeyCombination endTestCombination = new KeyCodeCombination(KeyCode.ESCAPE);

        view.getScene().setOnKeyPressed(event -> {
            if (restartCombination.match(event)) {
                restartGame();
            } else if (pauseCombination.match(event)) {
                pauseGame();
            } else if (endTestCombination.match(event)) {
                endGame();
            }
        });
    }

    private void addListeners() {
        view.getTopPanel().getLanguageListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedLanguage = availableLanguages.stream()
                            .filter(language -> language.getName().equals(newValue))
                            .findFirst()
                            .orElse(null);
                    System.out.println("Selected language: " + selectedLanguage.getName());
                    if (areGameSettingsComplete()) {
                        restartGame();
                    }
                }
        );

        view.getTopPanel().getTimeListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    time.setTimeOptions(timeOptions);
                    time.setCurrentTime(newValue);
                    view.getFooterPanel().getTimerLabel().setText(formatTime(Integer.parseInt(time.getCurrentTime())));

                    System.out.println("Selected time: " + time.getCurrentTime());
                    if (areGameSettingsComplete()) {
                        restartGame();
                    }
                }
        );
    }

    private void startGame() {
        view.getGamePanel().getInputArea().requestFocus();
        gameTimer = new GameTimer(Integer.parseInt(time.getCurrentTime()), this::updateTimeLabel);
        gameTimer.start();
        generateParagraph();
    }

    private void restartGame() {
        if (gameTimer != null) {
            gameTimer.stopTimer();
            gameTimer = null;
        }
        startGame();
    }

    private void pauseGame() {
        if (gameTimer != null) {
            if (gameTimer.isPaused()) {
                gameTimer.resumeTimer();
            } else {
                gameTimer.pauseTimer();
            }
        }
    }

    private void endGame() {
        if (gameTimer != null) {
            gameTimer.stopTimer();
            updateGameStats();
            showStatsChart();
        }
    }

    private void updateTimeLabel() {
        Platform.runLater(() -> {
            int currentTime = gameTimer.getTime();
            view.getFooterPanel().getTimerLabel().setText(formatTime(currentTime));
            if (currentTime == 0) {
                gameTimer.stopTimer();
                updateGameStats();
                showStatsChart();
            }
        });
    }

    private void updateGameStats() {
        String displayText = view.getGamePanel().getDisplayArea().getText();
        String userInput = view.getGamePanel().getInputArea().getText();
        gameStats.updateStats(displayText, userInput);
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
        List<String> languageNames = this.availableLanguages.stream()
                .map(Language::getName)
                .toList();

        view.getTopPanel().updateLists(languageNames, time.getTimeOptions());

        if (selectedLanguage == null) {
            int defaultIndex = languageNames.indexOf(selectedLanguage.getName());
            view.getTopPanel().getLanguageListView().getSelectionModel().select(defaultIndex);
        }

        if (time.getCurrentTime() == null) {
            int defaultIndex = timeOptions.indexOf(time.getCurrentTime());
            view.getTopPanel().getTimeListView().getSelectionModel().select(defaultIndex);
        }
        view.getFooterPanel().getTimerLabel().setText(formatTime(Integer.parseInt(time.getCurrentTime())));
    }

    private boolean areGameSettingsComplete() {
        return selectedLanguage != null && time.getCurrentTime() != null;
    }

    private void showStatsChart() {
        BarChart<String, Number> statsChart = createStatsChart();

        Stage chartStage = new Stage();
        chartStage.setTitle("Game Statistics");
        Scene scene = new Scene(statsChart, 800, 600);
        chartStage.setScene(scene);
        chartStage.show();
    }

    private BarChart<String, Number> createStatsChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        xAxis.setLabel("Category");
        yAxis.setLabel("Count");

        XYChart.Series<String, Number> correctSeries = new XYChart.Series<>();
        correctSeries.setName("Correct Words");
        correctSeries.getData().add(new XYChart.Data<>("Words", gameStats.getCorrectWordCount()));

        XYChart.Series<String, Number> incorrectSeries = new XYChart.Series<>();
        incorrectSeries.setName("Incorrect Words");
        incorrectSeries.getData().add(new XYChart.Data<>("Words", gameStats.getIncorrectWordCount()));

        barChart.getData().addAll(correctSeries, incorrectSeries);
        return barChart;
    }


}
