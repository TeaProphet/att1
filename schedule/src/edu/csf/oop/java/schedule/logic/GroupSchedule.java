package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.*;
import edu.csf.oop.java.schedule.logic.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class GroupSchedule {
    private final ArrayList<DailySchedule> schedules = new ArrayList<>();
    private final ArrayList<Student> students;
    private final int courseNumber;
    private final int groupNumber;
    private static final Logger logger = LoggerFactory.getLogger("info.logger");

    public GroupSchedule (int courseNumber, int groupNumber){
        for (int i = 0; i < 7; i++) {
            schedules.add(new DailySchedule());
        }
        this.students = new ArrayList<>();
        this.courseNumber = courseNumber;
        this.groupNumber = groupNumber;
    }

    public void addStudent(String fullNameWithInitials) throws WrongSurnameWithInitialsEntered {
        students.add(new Student(fullNameWithInitials, courseNumber, groupNumber));
        logger.info("Course " + courseNumber + ", group " + groupNumber + ": student " + fullNameWithInitials
                + " has been added");
    }

    public void removeStudent(String fullNameWithInitials) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFullName().equals(fullNameWithInitials)){
                students.remove(i);
                logger.info("Course " + courseNumber + ", group " + groupNumber + ": student " + fullNameWithInitials
                        + " has been removed");
                return;
            }
        }
        logger.error("ERROR: Student " + fullNameWithInitials + " in group " + groupNumber + " not found.");
    }

    public DailySchedule getDailySchedule(int numOfDay) throws WrongDayNumException {
        if (numOfDay < 0 || numOfDay > 7 || schedules.size() < numOfDay){
            logger.error("ERROR: Wrong day num entered. Group" + groupNumber + ", course " + courseNumber);
            throw new WrongDayNumException();
        }
        return schedules.get(numOfDay - 1);
    }

    public void addSubject(int dayNum, String lecturer, String subjectName,
                           String auditory, String subjectStart, String subjectEnd) throws WrongDayNumException, WrongTimeStringException, WrongStartEndException, WrongQueueOfSubjects {
        if (dayNum < 1 || dayNum > 7){
            logger.error("ERROR: Wrong day num entered. Group" + groupNumber + ", course " + courseNumber);
            throw new WrongDayNumException();
        }

        DailySchedule currentSchedule = schedules.get(dayNum - 1);
        MyDigitalTime subjectStartTime = TimeUtils.stringToTime(subjectStart);
        MyDigitalTime subjectEndTime = TimeUtils.stringToTime(subjectEnd);
        currentSchedule.addSubject(new SubjectUnit(subjectStartTime, subjectEndTime, lecturer, subjectName, auditory));
        logger.info("Course " + courseNumber + ", group " + groupNumber + ": subject " + subjectName + " has been added in " +
                TimeUtils.numOfDayToNameOfDay(dayNum));
    }

    public void removeSubject(int dayNum, int subjectNum) throws WrongDayNumException, WrongSubjectNum {
        if (dayNum < 1 || dayNum > 7){
            logger.error("ERROR: Wrong day num entered. Group" + groupNumber + ", course " + courseNumber);
            throw new WrongDayNumException();
        } else if (subjectNum < 1 || subjectNum > schedules.get(dayNum - 1).size()){
            logger.error("ERROR: Wrong subject num entered. Group" + groupNumber + ", course " + courseNumber);
            throw new WrongSubjectNum();
        }
        String subjectName = schedules.get(dayNum - 1).getSubject(subjectNum).getSubjectName();
        schedules.get(dayNum - 1).removeSubject(subjectNum - 1);
        logger.info("Course " + courseNumber + ", group " + groupNumber + ": subject " + subjectName +
                ", number in schedule " + subjectNum + ", has been removed from "
                + TimeUtils.numOfDayToNameOfDay(dayNum));
    }

    public boolean isNowLearning() throws WrongTimeCreated, WrongDayStringEntered {
        String dayOfWeek = TimeUtils.getCurrentDayOfWeek();
        DailySchedule dailySchedule = schedules.get(TimeUtils.dayOfWeekToNum(dayOfWeek) - 1);
        return dailySchedule.isLearningNow();
    }

    public String getCurrentSubject() throws SubjectNotFound, WrongTimeCreated, WrongDayStringEntered {
        String dayOfWeek = TimeUtils.getCurrentDayOfWeek();
        DailySchedule dailySchedule = schedules.get(TimeUtils.dayOfWeekToNum(dayOfWeek) - 1);
        logger.info("Trying to get current subject of group " + groupNumber + ", course " + courseNumber);
        return dailySchedule.getCurrentSubject().toString();
    }

    public String getWeeklySchedule() throws WrongDayNumException {
        StringBuilder weeklySchedule = new StringBuilder();
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).size() == 0){
                continue;
            }
            weeklySchedule.append(TimeUtils.numOfDayToNameOfDay(i + 1)).append(":\n");
            weeklySchedule.append(schedules.get(i).getStringSchedule()).append("\n");
        }
        return weeklySchedule.toString();
    }

    public int getNumberOfStudents () {
        return students.size();
    }

    public void clear(){
        schedules.clear();
        students.clear();
        logger.info("Schedule of group " + groupNumber + " course " + courseNumber + " has been cleared.");
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public int getCourseNumber() {
        return courseNumber;
    }
}
