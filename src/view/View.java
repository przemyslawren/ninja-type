package view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View extends BorderPane {
    private Stage primaryStage;
    private TopPanel topPanel;
    private GamePanel gamePanel;
    private FooterPanel footerPanel;

    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.topPanel = new TopPanel();
        this.gamePanel = new GamePanel();
        this.footerPanel = new FooterPanel();

        initializeMenu();

    }

    private void initializeMenu() {
        Scene scene = new Scene(this, 1024, 768);
        setTop(topPanel.getTopPanel());
        setCenter(gamePanel.getGamePanel());
        setBottom(footerPanel.getFooterPanel());

        Image icon = new Image("resources/ninja.png");
        scene.getStylesheets().add("styles/style.css");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ninja Type");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.setOnCloseRequest(event -> handleClose(event));

    }

    private void handleClose(WindowEvent event) {
        System.out.println("Closing...");
        System.exit(0);
        Platform.exit();
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