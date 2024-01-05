import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View(primaryStage);
        TextField typingArea = view.getDisplayArea();
        new Controller(model, view, typingArea);

        Platform.runLater(() -> view.show());
    }
}
