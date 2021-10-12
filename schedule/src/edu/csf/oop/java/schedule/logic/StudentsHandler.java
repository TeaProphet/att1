package edu.csf.oop.java.schedule.logic;

import java.util.ArrayList;

public class StudentsHandler {
    private ArrayList<Student> students;

    protected void addStudentAndGetID(Student student) {
        students.add(student);
    }

    protected ArrayList<Student> getStudents() {
        return students;
    }
}
