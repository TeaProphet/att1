package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.SubjectNotFound;
import edu.csf.oop.java.schedule.logic.exceptions.WrongQueueOfSubjects;
import edu.csf.oop.java.schedule.logic.exceptions.WrongTimeCreated;
import edu.csf.oop.java.schedule.logic.utils.TimeUtils;

import java.util.ArrayList;

public class DailySchedule {
    private ArrayList<SubjectUnit> schedule = new ArrayList<>();

    protected ArrayList<SubjectUnit> getSchedule() {
        return schedule;
    }

    public String getStringSchedule() {
        StringBuilder dailySchedule = new StringBuilder();
        for (int i = 0; i < schedule.size(); i++) {
            dailySchedule.append(i + 1).append(". ").append(schedule.get(i).toString()).append("\n");
        }
        return dailySchedule.toString();
    }

    protected void addSubject(SubjectUnit subject) throws WrongQueueOfSubjects {
        if (schedule.size() == 0){
            schedule.add(subject);
            return;
        } else if (subject.getSubjectEnd().isBefore(schedule.get(0).getSubjectStart())){
            schedule.add(0, subject);
            return;
        } else if (subject.getSubjectStart().isAfter(schedule.get(schedule.size() - 1).getSubjectEnd())){
            schedule.add(subject);
            return;
        } else {
            for (int i = schedule.size() - 1; i > 0 ; i--) {
                if (schedule.get(i).getSubjectStart().isAfter(subject.getSubjectEnd()) &&
                        schedule.get(i-1).getSubjectEnd().isBefore(subject.getSubjectStart())){
                    schedule.add(i, subject);
                    return;
                }
            }
        }
        throw new WrongQueueOfSubjects();
    }

    protected void removeSubject(int subjectNum) {
        schedule.remove(subjectNum);
    }

    protected void removeSubject(SubjectUnit subject){
        schedule.remove(subject);
    }

    protected void replaceSubject(int subjectNum, SubjectUnit subject){
        schedule.set(subjectNum, subject);
    }

    protected SubjectUnit getSubject(int subjectNum){
        return schedule.get(subjectNum - 1);
    }

    protected int size(){
        return schedule.size();
    }

    protected boolean isLearningNow() throws WrongTimeCreated {
        if (schedule.size() == 0){
            return false;
        }
        MyDigitalTime nowTime = TimeUtils.getNowTime();
        MyDigitalTime dayStarting = schedule.get(0).getSubjectStart();
        MyDigitalTime dayOver = schedule.get(schedule.size() - 1).getSubjectEnd();
        return nowTime.isAfter(dayStarting) && nowTime.isBefore(dayOver);
    }

    protected SubjectUnit getCurrentSubject() throws WrongTimeCreated, SubjectNotFound {
        MyDigitalTime nowTime = TimeUtils.getNowTime();
        for (SubjectUnit subject : schedule) {
            if (nowTime.isAfter(subject.getSubjectStart()) && nowTime.isBefore(subject.getSubjectEnd())){
                return subject;
            }
        }
        throw new SubjectNotFound();
    }

    public void clear(){
        schedule = new ArrayList<>();
    }
}
