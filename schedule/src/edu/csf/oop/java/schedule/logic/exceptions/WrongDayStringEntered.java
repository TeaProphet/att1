package edu.csf.oop.java.schedule.logic.exceptions;

public class WrongDayStringEntered extends Exception {
    public WrongDayStringEntered() {
        super("ERROR: Wrong day as string entered.");
    }
}
