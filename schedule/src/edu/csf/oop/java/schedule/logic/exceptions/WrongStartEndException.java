package edu.csf.oop.java.schedule.logic.exceptions;

public class WrongStartEndException extends Exception {
    public WrongStartEndException(){
        super("ERROR: Wrong start and end of subject.");
    }
}
