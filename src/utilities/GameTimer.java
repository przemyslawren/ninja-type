package utilities;



public class GameTimer extends Thread {
    private int time;
    private boolean isRunning;
    private final Runnable onTimeUpdate;

    public GameTimer(int time, Runnable onTimeUpdate) {
        this.time = time;
        this.onTimeUpdate = onTimeUpdate;
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000);
                time--;
                onTimeUpdate.run();
                System.out.println("Time remaining: " + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopTimer() {
        isRunning = false;
    }

    public int getTime() {
        return time;
    }
}
