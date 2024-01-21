package view;

import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

public class TopPanel extends HBox {
    private ListView<String> languageListView;
    private ListView<String> timeListView;

    public TopPanel() {
        super(100);
        initializeTopPanel();
    }

    private void initializeTopPanel() {
        languageListView = new ListView<>();
        timeListView = new ListView<>();

        languageListView.setOrientation(Orientation.HORIZONTAL);
        setListViewSettings(languageListView);

        timeListView.setOrientation(Orientation.VERTICAL);

        setListViewSettings(timeListView);

        getChildren().addAll(languageListView, timeListView);
        HBox.setHgrow(languageListView, Priority.ALWAYS);
        HBox.setHgrow(timeListView, Priority.ALWAYS);

        getStyleClass().add("top-panel");
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

    public ListView<String> getTimeListView() {
        return timeListView;
    }

    public HBox getTopPanel() {
        return this;
    }
}
