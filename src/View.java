import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;

public class View {
    final private Stage primaryStage;
    private ListView<String> languageListView;
    private ListView<String> timeListView;

    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.languageListView = new ListView<>();
        this.timeListView = new ListView<>();
        initializeMenu();
    }

    private void initializeMenu() {
        BorderPane rootLayout = new BorderPane();
        Scene scene = new Scene(rootLayout);
        Image icon = new Image("resources/ninja.png");
        primaryStage.getIcons().add(icon);

        scene.getStylesheets().add("styles/style.css");

        initializeTopPanel(rootLayout);
        initializeGamePanel(rootLayout);
        initializeFooterPane(rootLayout);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ninja Type");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
    }

    private void initializeTopPanel(BorderPane rootLayout) {
        HBox topPanel = new HBox(10);
        TitledPane languagePane = new TitledPane();
        languagePane.setText("Select language");
        languagePane.setContent(languageListView);

        TitledPane timePane = new TitledPane();
        timePane.setText("Select time");
        timePane.setContent(timeListView);

        setPanesSettings(languagePane);
        setPanesSettings(timePane);

        rootLayout.setTop(topPanel);
        topPanel.getChildren().addAll(languagePane, timePane);

        HBox.setHgrow(languagePane, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(timePane, javafx.scene.layout.Priority.ALWAYS);

        //TODO add button to refresh list of languages
    }

    private void initializeFooterPane(BorderPane rootLayout) {
        TitledPane footerTextPane = new TitledPane();
        footerTextPane.setText("• tab + enter - restart test\n" +
                "• ctrl + shift + p - pause\n" +
                "• esc - end test\n");
        rootLayout.setBottom(footerTextPane);
    }

    private void setPanesSettings(TitledPane pane) {
        pane.setExpanded(false);
        pane.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        pane.setMaxHeight(100);
    }

    private void initializeGamePanel(BorderPane rootLayout) {
        AnchorPane gamePane = new AnchorPane();
        TextField typingArea = new TextField();

        AnchorPane.setTopAnchor(typingArea, 50.0);
        AnchorPane.setLeftAnchor(typingArea, 20.0);
        AnchorPane.setRightAnchor(typingArea, 20.0);

        gamePane.getChildren().add(typingArea);
        rootLayout.setCenter(gamePane);

    }

    public void updateLists(List<String> languages, List<String> timeOptions) {
        languageListView.getItems().clear();
        languageListView.getItems().addAll(languages);

        timeListView.getItems().clear();
        timeListView.getItems().addAll(timeOptions);
    }


    public void show() {
        primaryStage.show();
    }
}
