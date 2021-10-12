package edu.csf.oop.java.schedule.logic;

import edu.csf.oop.java.schedule.logic.exceptions.WrongStartEndException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubjectUnit {
    private final MyDigitalTime subjectStart;
    private final MyDigitalTime subjectEnd;
    private final String lecturer;
    private final String auditory;
    private final String subjectName;
    private static final Logger logger = LoggerFactory.getLogger("info.logger");

    public SubjectUnit (MyDigitalTime subjectStart, MyDigitalTime subjectEnd, String lecturer, String subjectName,
                        String audience) throws WrongStartEndException {
        if (subjectEnd.isBefore(subjectStart)){
            throw new WrongStartEndException();
        }
        this.subjectStart = subjectStart;
        this.subjectEnd = subjectEnd;
        this.lecturer = lecturer;
        this.subjectName = subjectName;
        this.auditory = audience;
        logger.info("Subject " + this + " has been created");
    }

    public String getAuditory() {
        return auditory;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public MyDigitalTime getSubjectStart() {
        return subjectStart;
    }

    public MyDigitalTime getSubjectEnd() {
        return subjectEnd;
    }

    public String getStringSubjectStart() {
        return subjectStart.toString();
    }

    public String getStringSubjectEnd() {
        return subjectEnd.toString();
    }

    @Override
    public String toString() {
        return "[" + getStringSubjectStart() + " - " + getStringSubjectEnd() + "] " + subjectName + " " + lecturer + " " + auditory;
    }
}
