package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.CourseAlreadyExistsException;
import edu.csf.oop.java.schedule.logic.exceptions.CourseNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalScheduleHandler {
    private final HashMap<Integer, CourseSchedule> allCoursesSchedules = new HashMap<>();

    public void createCourse (int courseNumber, int numberOfGroups) throws CourseAlreadyExistsException {
        if (allCoursesSchedules.containsKey(courseNumber)){
            throw new CourseAlreadyExistsException();
        } else {
            allCoursesSchedules.put(courseNumber, new CourseSchedule(courseNumber, numberOfGroups));
        }
    }

    public void removeCourse(int courseNumber) throws CourseNotFoundException {
        if (!allCoursesSchedules.containsKey(courseNumber)){
            throw new CourseNotFoundException();
        } else {
            allCoursesSchedules.remove(courseNumber);
        }
    }

    public CourseSchedule getCourseSchedule (int numberOfCourse) throws CourseNotFoundException {
        if (allCoursesSchedules.containsKey(numberOfCourse)){
            return allCoursesSchedules.get(numberOfCourse);
        } else {
            throw new CourseNotFoundException();
        }
    }

    public HashMap<Integer, Integer> getNumberOfStudentsForEachCourse() {
        HashMap<Integer, Integer> numberOfStudentsForEachCourse = new HashMap<>();
        for (int numberOfCourse: allCoursesSchedules.keySet()) {
            numberOfStudentsForEachCourse.put(numberOfCourse, allCoursesSchedules.get(numberOfCourse).getNumberOfStudents());
        }
        return numberOfStudentsForEachCourse;
    }

    public void clear() {
        allCoursesSchedules.clear();
    }
}
