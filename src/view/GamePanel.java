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

        inputArea.textProperty().addListener((observable, oldValue, newValue) -> compareTexts());
    }

    private void compareTexts() {
        String displayText = displayArea.getText();
        String userInput = inputArea.getText();

        if (displayText.startsWith(userInput)) {
            // Correct so far
            // You can add more logic here, such as updating the UI to show correctness
            System.out.println("Correct so far");
            inputArea.setStyle("-fx-text-fill: green;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-color: transparent;");
            String typedText = displayText.substring(0, userInput.length());
            String remainingText = displayText.substring(userInput.length());
            displayArea.setText(typedText + remainingText);
        } else {
            // Incorrect input
            // You can add more logic here, such as highlighting the error
            System.out.println("Incorrect input");
            inputArea.setStyle("-fx-text-fill: red;"
                    + "-fx-background-color: transparent;"
                    + "-fx-border-color: transparent;");
        }
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
