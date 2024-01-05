import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        languageListView.setOrientation(Orientation.HORIZONTAL);
        setListViewSettings(languageListView);

        timeListView.setOrientation(Orientation.VERTICAL);

        setListViewSettings(timeListView);

        topPanel.getChildren().addAll(languageListView, timeListView);
        HBox.setHgrow(languageListView, Priority.ALWAYS);
        HBox.setHgrow(timeListView, Priority.ALWAYS);

        rootLayout.setTop(topPanel);

        //TODO add button to refresh list of languages
    }

    private void initializeFooterPane(BorderPane rootLayout) {
        TitledPane footerTextPane = new TitledPane();
        footerTextPane.setText("• tab + enter - restart test\n" +
                "• ctrl + shift + p - pause\n" +
                "• esc - end test\n");
        rootLayout.setBottom(footerTextPane);
    }


    private void setListViewSettings(ListView<String> listView) {
        listView.setMaxHeight(70);
    }

    private void initializeGamePanel(BorderPane rootLayout) {
        AnchorPane gamePane = new AnchorPane();
        TextField typingArea = new TextField();

        AnchorPane.setTopAnchor(typingArea, 50.0);
        AnchorPane.setLeftAnchor(typingArea, 20.0);
        AnchorPane.setRightAnchor(typingArea, 20.0);

        System.out.println(typingArea.heightProperty());

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
