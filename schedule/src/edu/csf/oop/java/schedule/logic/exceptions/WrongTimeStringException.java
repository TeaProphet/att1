package edu.csf.oop.java.schedule.logic.exceptions;

public class WrongTimeStringException extends Exception {
    public WrongTimeStringException() {
        super("ERROR: Wrong date entered as string");
    }
}
