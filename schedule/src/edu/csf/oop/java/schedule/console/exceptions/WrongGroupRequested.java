package edu.csf.oop.java.schedule.console.exceptions;

public class WrongGroupRequested extends Exception{
    public WrongGroupRequested() {
        super("ERROR: Selected course doesn't exist");
    }
}
