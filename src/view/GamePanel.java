package view;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GamePanel {

    AnchorPane gamePanel;
    private TextField displayArea;
    private TextField typingArea;

    public GamePanel() {
        initializeGamePanel();
    }

    private void initializeGamePanel() {
        gamePanel = new AnchorPane();
        displayArea = new TextField();
        typingArea = new TextField();

        displayArea.setEditable(false);
        displayArea.setOpacity(0.5);

        AnchorPane.setTopAnchor(displayArea, 100.0);
        AnchorPane.setLeftAnchor(displayArea, 50.0);
        AnchorPane.setRightAnchor(displayArea, 50.0);
        AnchorPane.setBottomAnchor(displayArea, 100.0);

        typingArea.setStyle("-fx-background-color: transparent;");

        AnchorPane.setTopAnchor(typingArea, 100.0);
        AnchorPane.setLeftAnchor(typingArea, 50.0);
        AnchorPane.setRightAnchor(typingArea, 50.0);
        AnchorPane.setBottomAnchor(typingArea, 100.0);

        gamePanel.getChildren().addAll(displayArea, typingArea);
    }

    public TextField getDisplayArea() {
        return displayArea;
    }

    public TextField getTypingArea() {
        return typingArea;
    }

    public AnchorPane getGamePanel() {
        return gamePanel;
    }

}
