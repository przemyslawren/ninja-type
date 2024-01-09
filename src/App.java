import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.Time;
import view.View;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Time time = new Time();
        View view = new View(primaryStage);
        new Controller(time, view);

        Platform.runLater(() -> view.show());
    }
}
