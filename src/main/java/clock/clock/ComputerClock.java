package clock.clock;

import clock.devices.ClockDevice;
import clock.services.ComputerTimer;


public class ComputerClock {

    public static void main(String args[])
    {
        ClockDevice clockDev = new ClockDevice();

        clockDev.setTimer(new ComputerTimer());

        ClockFrame clock;
        if (args.length > 0) {
            clock= new ClockFrame(clockDev, args[0]);
        } else {
            clock = new ClockFrame(clockDev);
        }
        clock.start();    }
}