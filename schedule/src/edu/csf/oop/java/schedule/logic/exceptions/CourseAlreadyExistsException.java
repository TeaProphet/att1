package edu.csf.oop.java.schedule.logic.exceptions;

public class CourseAlreadyExistsException extends Exception {
    public CourseAlreadyExistsException () {
        super("ERROR: Course already exists");
    }
}
