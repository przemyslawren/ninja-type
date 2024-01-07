package view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View {
    private Stage primaryStage;
    private TopPanel topPanel;
    private GamePanel gamePanel;
    private FooterPanel footerPanel;
    BorderPane rootLayout;

    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.rootLayout = new BorderPane();
        this.topPanel = new TopPanel();
        this.gamePanel = new GamePanel();
        this.footerPanel = new FooterPanel();

        initializeMenu();
    }

    private void initializeMenu() {
        Scene scene = new Scene(rootLayout);
        rootLayout.setTop(topPanel.getTopPanel());
        rootLayout.setCenter(gamePanel.getGamePanel());
        rootLayout.setBottom(footerPanel.getFooterPanel());

        Image icon = new Image("resources/ninja.png");
        scene.getStylesheets().add("styles/style.css");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ninja Type");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public FooterPanel getFooterPanel() {
        return footerPanel;
    }


    public void show() {
        primaryStage.show();
    }

}