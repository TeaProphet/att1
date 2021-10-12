package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.console.exceptions.WrongGroupRequested;
import edu.csf.oop.java.schedule.logic.exceptions.GroupAlreadyExistsException;

import java.util.Collection;
import java.util.HashMap;

public class CourseSchedule {
    private final HashMap<Integer, GroupSchedule> courseSchedule = new HashMap<>();
    private final int courseNumber;

    public CourseSchedule (int courseNumber, int numberOfGroups) {
        this.courseNumber = courseNumber;
        for (int i = 1; i <= numberOfGroups; i++) {
            courseSchedule.put(i, new GroupSchedule(courseNumber, i));
        }
    }

    public GroupSchedule getScheduleForGroup (int groupNumber) throws WrongGroupRequested {
        if (isGroupThere(groupNumber)){
            return courseSchedule.get(groupNumber);
        }
        throw new WrongGroupRequested();
    }

    public void addGroup(int groupNumber) throws GroupAlreadyExistsException {
        if (isGroupThere(groupNumber)){
            throw new GroupAlreadyExistsException();
        } else {
            courseSchedule.put(groupNumber, new GroupSchedule(courseNumber, groupNumber));
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
    }

    public void clearGroupSchedule (int groupNumber) {
        courseSchedule.get(groupNumber).clear();
    }

    private boolean isGroupThere (int groupNumber) {
        return courseSchedule.containsKey(groupNumber);
    }

    public int getNumberOfGroups() {
        return courseSchedule.size();
    }

    public Collection<GroupSchedule> getGroupScheduleList(){
        return courseSchedule.values();
    }
}
