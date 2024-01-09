package view;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class FooterPanel {
    BorderPane footerPanel;
    Label shortcutsPanel;
    Label timeLabel;

    public FooterPanel() {
        initializeFooterPanel();
    }

    private void initializeFooterPanel() {
        footerPanel = new BorderPane();
        shortcutsPanel = new Label();
        shortcutsPanel.setText("• tab + enter - restart test\n" +
                "• ctrl + shift + p - pause\n" +
                "• esc - end test\n");
        timeLabel = new Label("00:30");
        timeLabel.getStyleClass().add("time-label");


        footerPanel.setLeft(shortcutsPanel);
        footerPanel.setRight(timeLabel);
        footerPanel.getStyleClass().add("footer-panel");
    }

    public BorderPane getFooterPanel() {
        return footerPanel;
    }

    public Label getTimerLabel() {
        return timeLabel;
    }

}
