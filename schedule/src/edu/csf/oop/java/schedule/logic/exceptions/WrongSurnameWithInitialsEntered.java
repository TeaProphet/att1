package edu.csf.oop.java.schedule.logic.exceptions;

public class WrongSurnameWithInitialsEntered extends Exception {
    public WrongSurnameWithInitialsEntered() {
        super("ERROR: Wrong surname with initials entered.");
    }
}
