package utilities;

public class GameTimer extends Thread {
    private volatile int time;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private final Runnable onTimeUpdate;

    public GameTimer(int time, Runnable onTimeUpdate) {
        this.time = time;
        this.onTimeUpdate = onTimeUpdate;
        isRunning = true;
        isPaused = false;
    }

    @Override
    public void run() {
        while (isRunning && time > 0) {
            try {
                if (!isPaused) {
                    Thread.sleep(1000);
                    time--;
                    onTimeUpdate.run();
                    System.out.println("Time remaining: " + time);

                    if (time == 0) {
                        stopTimer();
                    }
                } else {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                if (!isPaused) {
                    System.out.println("Timer interrupted");
                    break;
                }
            }
        }
    }

    public void pauseTimer() {
        isPaused = true;
    }

    public void resumeTimer() {
        isPaused = false;
    }

    public void stopTimer() {
        isRunning = false;
        this.interrupt();
    }

    public int getTime() {
        return time;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
