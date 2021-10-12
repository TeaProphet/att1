package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.*;
import edu.csf.oop.java.schedule.logic.utils.TimeUtils;

import java.util.ArrayList;

public class GroupSchedule {
    private ArrayList<DailySchedule> schedules = new ArrayList<>();
    private ArrayList<Student> students;
    private final int courseNumber;
    private final int groupNumber;

    public GroupSchedule (int courseNumber, int groupNumber){
        for (int i = 0; i < 7; i++) {
            schedules.add(new DailySchedule());
        }
        this.students = new ArrayList<>();
        this.courseNumber = courseNumber;
        this.groupNumber = groupNumber;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStudent(String fullNameWithInitials) throws WrongSurnameWithInitialsEntered {
        students.add(new Student(fullNameWithInitials, courseNumber, groupNumber));
    }

    public DailySchedule getDailySchedule(int numOfDay) throws WrongDayNumException {
        if (numOfDay < 0 || numOfDay > 7 || schedules.size() < numOfDay){
            throw new WrongDayNumException();
        }
        return schedules.get(numOfDay - 1);
    }

    public void addSubject(int dayNum, String lecturer, String subjectName,
                           String auditory, String subjectStart, String subjectEnd) throws WrongDayNumException, WrongTimeStringException, WrongStartEndException, WrongQueueOfSubjects {
        if (dayNum < 1 || dayNum > 7){
            throw new WrongDayNumException();
        }

        DailySchedule currentSchedule = schedules.get(dayNum - 1);
        MyDigitalTime subjectStartTime = TimeUtils.stringToTime(subjectStart);
        MyDigitalTime subjectEndTime = TimeUtils.stringToTime(subjectEnd);
        currentSchedule.addSubject(new SubjectUnit(subjectStartTime, subjectEndTime, lecturer, subjectName, auditory));
    }

    public void removeSubject(int dayNum, int subjectNum) throws WrongDayNumException, WrongSubjectNum {
        if (dayNum < 1 || dayNum > 7){
            throw new WrongDayNumException();
        } else if (subjectNum < 1 || subjectNum > schedules.get(dayNum - 1).size()){
            throw new WrongSubjectNum();
        }
        schedules.get(dayNum - 1).removeSubject(subjectNum - 1);
    }

    public boolean isNowLearning() throws WrongDayNumException, WrongTimeCreated {
        String dayOfWeek = TimeUtils.getCurrentDayOfWeek();
        DailySchedule dailySchedule = schedules.get(TimeUtils.dayOfWeekToNum(dayOfWeek) - 1);
        return dailySchedule.isLearningNow();
    }

    public String getCurrentSubject() throws WrongDayNumException, SubjectNotFound, WrongTimeCreated {
        String dayOfWeek = TimeUtils.getCurrentDayOfWeek();
        DailySchedule dailySchedule = schedules.get(TimeUtils.dayOfWeekToNum(dayOfWeek) - 1);
        return dailySchedule.getCurrentSubject().toString();
    }

    public String getWeeklySchedule(){
        StringBuilder weeklySchedule = new StringBuilder();
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).size() == 0){
                continue;
            }
            try {
                weeklySchedule.append(TimeUtils.numOfDayToNameOfDay(i + 1)).append(":\n");
            } catch (WrongDayNumException e) {
                e.printStackTrace();
            }
            weeklySchedule.append(schedules.get(i).getStringSchedule()).append("\n");
        }
        return weeklySchedule.toString();
    }

    public int getNumberOfStudents () {
        return students.size();
    }

    public void clear(){
        schedules = new ArrayList<>();
        students = new ArrayList<>();
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
