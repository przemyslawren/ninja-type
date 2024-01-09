package view;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GamePanel extends AnchorPane {
    private TextField displayArea;
    private TextField inputArea;

    public GamePanel() {
        initializeGamePanel();
    }

    private void initializeGamePanel() {
        displayArea = new TextField();
        inputArea = new TextField();

        displayArea.setEditable(false);
        displayArea.setOpacity(0.5);

        setTopAnchor(displayArea, 100.0);
        setLeftAnchor(displayArea, 0.0);
        setRightAnchor(displayArea, 0.0);
        setBottomAnchor(displayArea, 100.0);

        inputArea.setStyle("-fx-background-color: transparent;");

        setTopAnchor(inputArea, 100.0);
        setLeftAnchor(inputArea, 0.0);
        setRightAnchor(inputArea, 0.0);
        setBottomAnchor(inputArea, 100.0);

        getChildren().addAll(displayArea, inputArea);
    }

    public TextField getDisplayArea() {
        return displayArea;
    }

    public TextField getInputArea() {
        return inputArea;
    }

    public AnchorPane getGamePanel() {
        return this;
    }

}
