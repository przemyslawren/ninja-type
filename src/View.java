
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View {
    private StackPane rootLayout;
    private Stage primaryStage;

    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }
    private void initialize() {
        rootLayout = new StackPane();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setTitle("Ninja Type");
    }

    public void show() {
        primaryStage.show();
    }
}
