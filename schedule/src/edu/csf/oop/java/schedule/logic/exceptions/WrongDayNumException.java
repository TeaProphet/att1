package edu.csf.oop.java.schedule.logic.exceptions;

public class WrongDayNumException extends Exception{
    public WrongDayNumException () {
        super("ERROR: Wrong num of day entered.");
    }
}
