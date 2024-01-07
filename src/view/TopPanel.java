package view;

import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

public class TopPanel {
    private HBox topPanel;
    private ListView<String> languageListView;
    private ListView<String> timeListView;

    public TopPanel() {
        initializeTopPanel();
    }

    private void initializeTopPanel() {
        topPanel = new HBox(10);
        languageListView = new ListView<>();
        timeListView = new ListView<>();

        languageListView.setOrientation(Orientation.HORIZONTAL);
        setListViewSettings(languageListView);

        timeListView.setOrientation(Orientation.VERTICAL);

        setListViewSettings(timeListView);

        topPanel.getChildren().addAll(languageListView, timeListView);
        HBox.setHgrow(languageListView, Priority.ALWAYS);
        HBox.setHgrow(timeListView, Priority.ALWAYS);

        //TODO add button to refresh list of languages
    }

    private void setListViewSettings(ListView<String> listView) {
        listView.setMaxHeight(70);
    }

    public void updateLists(List<String> languages, List<String> timeOptions) {
        languageListView.getItems().clear();
        languageListView.getItems().addAll(languages);

        timeListView.getItems().clear();
        timeListView.getItems().addAll(timeOptions);
    }


    public ListView<String> getLanguageListView() {
        return languageListView;
    }

    public HBox getTopPanel() {
        return topPanel;
    }
}
