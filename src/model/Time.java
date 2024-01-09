package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Time {
    private List<String> timeOptions;
    private String defaultTime = "30";

    public Time() {
        timeOptions = new ArrayList<>(Arrays.asList("30", "60", "90", "120"));;
    }

    public void setDefaultTime(String defaultTime) {
        this.defaultTime = defaultTime;
    }

    public String getDefaultTime() {
        return defaultTime;
    }

    public void setTimeOptions(List<String> timeOptions) {

        this.timeOptions = timeOptions;
    }


    public List<String> getTimeOptions() {
        return timeOptions;
    }
}
