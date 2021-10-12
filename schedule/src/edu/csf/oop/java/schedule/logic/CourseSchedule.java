package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.GroupAlreadyExistsException;
import edu.csf.oop.java.schedule.logic.exceptions.GroupNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;

public class CourseSchedule {
    private final HashMap<Integer, GroupSchedule> courseSchedule = new HashMap<>();
    private final int courseNumber;
    private static final Logger logger = LoggerFactory.getLogger("info.logger");

    public CourseSchedule (int courseNumber, int numberOfGroups) {
        this.courseNumber = courseNumber;
        for (int i = 1; i <= numberOfGroups; i++) {
            courseSchedule.put(i, new GroupSchedule(courseNumber, i));
        }
        logger.info("Course number " + courseNumber + " with number of groups " + numberOfGroups + " has been created.");
    }

    public GroupSchedule getScheduleForGroup (int groupNumber) throws GroupNotFoundException {
        if (isGroupThere(groupNumber)){
            return courseSchedule.get(groupNumber);
        }
        logger.error("ERROR: Group " + groupNumber + " has been created.");
        throw new GroupNotFoundException();
    }

    public void addGroup(int groupNumber) throws GroupAlreadyExistsException {
        if (isGroupThere(groupNumber)){
            logger.error("ERROR: Group " + groupNumber + " already exists.");
            throw new GroupAlreadyExistsException();
        } else {
            courseSchedule.put(groupNumber, new GroupSchedule(courseNumber, groupNumber));
            logger.info("Course " + courseNumber + ": group " + groupNumber + " has been created.");
        }
    }

    public void removeGroup(int groupNumber) throws GroupNotFoundException {
        if (isGroupThere(groupNumber)){
            courseSchedule.remove(groupNumber);
            logger.info("Course " + courseNumber + ": group " + groupNumber + " has been removed.");
        } else {
            logger.error("ERROR: Group " + groupNumber + " doesn't exist.");
            throw new GroupNotFoundException();
        }
    }

    public int getNumberOfStudents() {
        int numberOfStudents = 0;
        for (GroupSchedule groupSchedule: courseSchedule.values()) {
            numberOfStudents += groupSchedule.getNumberOfStudents();
        }
        return numberOfStudents;
    }

    public void clearCourseSchedule() {
        courseSchedule.clear();
        logger.info("Course " + courseNumber + " schedule has been cleared.");
    }

    public void clearGroupSchedule (int groupNumber) {
        courseSchedule.get(groupNumber).clear();
        logger.info("Course" + courseNumber + " schedule: group " + groupNumber + " schedule has been cleared.");
    }

    private boolean isGroupThere (int groupNumber) {
        return courseSchedule.containsKey(groupNumber);
    }

    public Collection<GroupSchedule> getGroupScheduleList(){
        return courseSchedule.values();
    }
}
