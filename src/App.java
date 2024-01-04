import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View(primaryStage);
        new Controller(model, view);

        Platform.runLater(() -> view.show());
    }
}
