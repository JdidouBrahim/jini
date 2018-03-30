package clock.services;

import java.util.Date;

public class ComputerTimer implements Timer {

    public ComputerTimer() {
    }

    public void setTime(Date t) {
        // void
    }

    public Date getTime() {
        return new Date();
    }

    public boolean isValidTime() {
        return true;
    }

}
