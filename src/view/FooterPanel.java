package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FooterPanel extends BorderPane {
    Label shortcutsPanel;
    Label timeLabel;

    public FooterPanel() {
        initializeFooterPanel();
    }

    private void initializeFooterPanel() {
        shortcutsPanel = new Label();
        shortcutsPanel.setText("• ctrl + enter - restart test\n" +
                "• ctrl + shift + p - pause\n" +
                "• esc - end test\n");
        timeLabel = new Label("00:00:00");
        timeLabel.getStyleClass().add("time-label");

        setLeft(shortcutsPanel);
        setRight(timeLabel);
        getStyleClass().add("footer-panel");
    }

    public BorderPane getFooterPanel() {
        return this;
    }

    public Label getTimerLabel() {
        return timeLabel;
    }

}
