package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.WrongTimeCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDigitalTime {
    private final int hours;
    private final int minutes;
    private static final Logger logger = LoggerFactory.getLogger("info.logger");

    public MyDigitalTime(int hours, int minutes) throws WrongTimeCreated {
        if (hours < 0 || minutes < 0 || hours > 23 || minutes > 60){
            logger.error("ERROR: Wrong time entered.");
            throw new WrongTimeCreated();
        }
        this.hours = hours;
        this.minutes = minutes;
        logger.info("Exemplar of class MyDigitalTime created.");
    }

    public boolean isBefore(MyDigitalTime anotherTime){
        if (this.hours > anotherTime.hours){
            return false;
        } else if (this.hours == anotherTime.hours){
            return this.minutes <= anotherTime.minutes;
        } else {
            return true;
        }
    }

    public boolean isAfter(MyDigitalTime anotherTime){
        if (this.hours < anotherTime.hours){
            return false;
        } else if (this.hours == anotherTime.hours){
            return this.minutes >= anotherTime.minutes;
        } else {
            return true;
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean equal(MyDigitalTime anotherTime){
        return this.hours == anotherTime.hours && this.minutes == anotherTime.minutes;
    }

    @Override
    public String toString() {
        String hours = String.valueOf(this.hours);
        String minutes = String.valueOf(this.minutes);
        if (hours.length() == 1){
            hours = 0 + hours;
        }
        if (minutes.length() == 1){
            minutes = 0 + minutes;
        }
        return hours + ":" + minutes;
    }
}
