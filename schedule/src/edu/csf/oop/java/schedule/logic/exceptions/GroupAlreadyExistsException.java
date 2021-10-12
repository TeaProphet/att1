package edu.csf.oop.java.schedule.logic.exceptions;

public class GroupAlreadyExistsException extends Exception {
    public GroupAlreadyExistsException () {
        super("ERROR: Group already exists in global schedule.");
    }
}
