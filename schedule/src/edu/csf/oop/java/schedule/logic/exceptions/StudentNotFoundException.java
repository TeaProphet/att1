package edu.csf.oop.java.schedule.logic.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException() {
        super("ERROR: Student not found exception");
    }
}
