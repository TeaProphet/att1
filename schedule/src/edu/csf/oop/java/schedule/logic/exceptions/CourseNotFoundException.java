package edu.csf.oop.java.schedule.logic.exceptions;

public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(){
        super("ERROR: Selected course doesn't exist.");
    }
}
