package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Time {
    private List<String> timeOptions;
    private String defaultTime = "30";
    private String currentTime = defaultTime;

    public Time() {
        timeOptions = new ArrayList<>(Arrays.asList("15", "20", "45", "60", "90", "120", "300"
        ));
    }

    public void setCurrentTime(String time) {
        this.currentTime = time;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setTimeOptions(List<String> timeOptions) {

        this.timeOptions = timeOptions;
    }


    public List<String> getTimeOptions() {
        return timeOptions;
    }
}
