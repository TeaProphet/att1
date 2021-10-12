package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.WrongSurnameWithInitialsEntered;

import java.util.regex.Pattern;

public class Student {
    private final String fullName;
    private final Integer courseNumber;
    private final Integer groupNumber;

    protected Student (String fullName, int courseNumber, int groupNumber) throws WrongSurnameWithInitialsEntered {
        String regexp = "[A-Z][a-z]{1,20}\s[A-Z]\\.[A-Z]\\.";
        if (Pattern.matches(regexp, fullName)){
            this.fullName = fullName;
            this.courseNumber = courseNumber;
            this.groupNumber = groupNumber;
            return;
        }
        throw new WrongSurnameWithInitialsEntered();
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }
}
