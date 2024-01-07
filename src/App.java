import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.LanguageModel;
import model.TimeOptionsModel;
import view.View;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        LanguageModel languageModel = new LanguageModel();
        TimeOptionsModel timeOptionsModel = new TimeOptionsModel();
        View view = new View(primaryStage);
        new Controller(languageModel, timeOptionsModel, view);

        Platform.runLater(() -> view.show());
    }
}
