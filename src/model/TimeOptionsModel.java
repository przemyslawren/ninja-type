package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeOptionsModel {
    private List<String> timeOptions;

    public TimeOptionsModel() {
        timeOptions = new ArrayList<>(Arrays.asList("15", "30", "45", "60","90","120","300"));
    }


    public List<String> getTimeOptions() {
        return timeOptions;
    }
}
