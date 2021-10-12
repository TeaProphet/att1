package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.CourseAlreadyExistsException;
import edu.csf.oop.java.schedule.logic.exceptions.CourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GlobalScheduleHandler {
    private final HashMap<Integer, CourseSchedule> allCoursesSchedules = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger("info.logger");

    public void createCourse (int courseNumber, int numberOfGroups) throws CourseAlreadyExistsException {
        if (allCoursesSchedules.containsKey(courseNumber)){
            logger.error("ERROR: Course " + courseNumber + " already exists.");
            throw new CourseAlreadyExistsException();
        } else {
            allCoursesSchedules.put(courseNumber, new CourseSchedule(courseNumber, numberOfGroups));
        }
    }

    public void removeCourse(int courseNumber) throws CourseNotFoundException {
        if (!allCoursesSchedules.containsKey(courseNumber)){
            logger.error("ERROR: Course " + courseNumber + " not found.");
            throw new CourseNotFoundException();
        } else {
            allCoursesSchedules.remove(courseNumber);
            logger.info("Course number " + courseNumber + " has been removed.");
        }
    }

    public CourseSchedule getCourseSchedule (int courseNumber) throws CourseNotFoundException {
        if (allCoursesSchedules.containsKey(courseNumber)){
            return allCoursesSchedules.get(courseNumber);
        } else {
            logger.error("ERROR: Course " + courseNumber + " not found.");
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
