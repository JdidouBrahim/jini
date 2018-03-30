package clock.services;

import clock.Ticker;

import java.util.Date;

public class TickerTimer implements   Timer {

    private Date time;
    private boolean isValid;
    private Ticker ticker;

    /**
     * Constructor with no starting time has
     * invalid timer and any time
     */
    public TickerTimer() {
        time = new Date(0);
        isValid = false;
        ticker = new Ticker(time);
        ticker.start();
    }

    public TickerTimer(Date t) {
        time = t;
        isValid = true;
        ticker = new Ticker(time);
        ticker.start();
    }

    public void setTime(Date t) {
        System.out.println("Setting time to " + t);
        time = t;
        isValid = true;
        if (ticker != null) {
            ticker.stopRunning();
        }
        ticker = new Ticker(time);
        ticker.start();
    }

    public Date getTime() {
        return ticker.getTime();
    }

    public boolean isValidTime() {
        if (isValid) {
            return true;
        } else {
            return false;
        }
    }

}
