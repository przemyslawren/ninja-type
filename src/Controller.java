import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        updateView();
    }

    private void updateView() {
        List<String> languages = model.getAvailableLanguages();
        List<String> timeOptions = model.getTimeOptions();

        view.updateLists(languages, timeOptions);
    }

}
