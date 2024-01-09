package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InstructionPanel extends VBox {

    public InstructionPanel() {
        super(10);
        initializeInstructionPanel();
    }

    private void initializeInstructionPanel() {
        Label instructionLabel = new Label("Choose the settings and write as fast as you can!");
        Button startButton = new Button("Start");

        getChildren().addAll(instructionLabel, startButton);
        getStyleClass().add("instruction-panel");
    }

    public VBox getInstructionPanel() {
        return this;
    }

    public Button getStartButton() {
        return (Button) getChildren().get(1);
    }
}
