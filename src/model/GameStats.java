package model;

public class GameStats {
    private int correctWordCount;
    private int incorrectWordCount;
    private long startTime;

    public GameStats() {
        resetStats();
    }

    public void resetStats() {
        this.correctWordCount = 0;
        this.incorrectWordCount = 0;
        this.startTime = System.currentTimeMillis();
    }

    public void updateStats(String displayText, String userInput) {
        String[] displayWords = displayText.split("\\s+");
        String[] inputWords = userInput.split("\\s+");

        correctWordCount = 0;
        incorrectWordCount = 0;

        for (int i = 0; i < inputWords.length; i++) {
            if (i < displayWords.length) {
                if (inputWords[i].equals(displayWords[i])) {
                    correctWordCount++;
                } else {
                    incorrectWordCount++;
                }
            } else {
                incorrectWordCount++;
            }
        }
    }

    public int getCorrectWordCount() {
        return correctWordCount;
    }

    public int getIncorrectWordCount() {
        return incorrectWordCount;
    }

    public double calculateWPM() {
        double elapsedTimeInMinutes = (System.currentTimeMillis() - startTime) / 60000.0;
        return correctWordCount / elapsedTimeInMinutes;
    }

    public String getStats() {
        return "Correct Words: " + getCorrectWordCount() +
                ", Incorrect Words: " + getIncorrectWordCount() +
                ", WPM: " + calculateWPM();
    }
}
