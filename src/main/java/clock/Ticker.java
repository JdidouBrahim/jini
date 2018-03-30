package clock;

import java.util.Date;

public class Ticker extends Thread {

    private Date time;
    private boolean keepRunning = true;

    public Ticker(Date t) {
        time = t;
    }

    public Date getTime() {
        return time;
    }

    public void run() {
        while (keepRunning) {
            try {
                sleep(1000);
            } catch(InterruptedException e) {
            }
            time = new Date(time.getTime() + 1000);
        }
    }

    public void stopRunning() {
        keepRunning = false;
    }

}
