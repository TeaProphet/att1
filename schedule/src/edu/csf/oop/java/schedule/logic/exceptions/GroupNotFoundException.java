package edu.csf.oop.java.schedule.logic.exceptions;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException () {
        super("ERROR: Group not found.");
    }
}
