package view;

import javafx.scene.control.TitledPane;

public class FooterPanel {

    TitledPane footerPanel;

    public FooterPanel() {
        initializeFooterPanel();
    }

    private void initializeFooterPanel() {
        footerPanel = new TitledPane();
        footerPanel.setText("• tab + enter - restart test\n" +
                "• ctrl + shift + p - pause\n" +
                "• esc - end test\n");
    }

    public TitledPane getFooterPanel() {
        return footerPanel;
    }

}
