package edu.csf.oop.java.schedule.console.exceptions;

public class WrongCourseSelected extends Exception{
    public WrongCourseSelected(){
        super("ERROR: Selected course doesn't exist");
    }
}
