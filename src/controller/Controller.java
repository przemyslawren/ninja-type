package controller;

import model.LanguageModel;
import model.TimeOptionsModel;
import view.FooterPanel;
import view.GamePanel;
import view.TopPanel;
import view.View;

import java.util.List;

public class Controller {
    private LanguageModel languageModel;
    private TimeOptionsModel timeOptionsModel;
    private View view;
    private TopPanel topPanel;
    private GamePanel gamePanel;
    private FooterPanel footerPanel;

    public Controller(LanguageModel languageModel, TimeOptionsModel timeOptionsModel,
                      View view) {

        this.languageModel = languageModel;
        this.timeOptionsModel = timeOptionsModel;
        this.view = view;
        this.topPanel = view.getTopPanel();
        this.gamePanel = view.getGamePanel();
        this.footerPanel = view.getFooterPanel();

        updateView();

        // Przykładowa logika reagująca na zmianę języka

    }

    private void updateTypingArea(String language) {
        List<String> words = languageModel.getRandomWords(language, 30);
        gamePanel.getTypingArea().setText(String.join(" ", words));
    }

    private void updateView() {
        List<String> languages = languageModel.getAvailableLanguages();
        List<String> timeOptions = timeOptionsModel.getTimeOptions();

        view.getTopPanel().updateLists(languages, timeOptions);
        view.getTopPanel().getLanguageListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateTypingArea(newValue)
        );
    }

}
