package edu.csf.oop.java.schedule.logic.utils;

import edu.csf.oop.java.schedule.logic.MyDigitalTime;
import edu.csf.oop.java.schedule.logic.exceptions.WrongDayNumException;
import edu.csf.oop.java.schedule.logic.exceptions.WrongTimeCreated;
import edu.csf.oop.java.schedule.logic.exceptions.WrongTimeStringException;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtils {
    public static int dayOfWeekToNum (String dayOfWeek) throws WrongDayNumException {
        switch (dayOfWeek.toLowerCase()){
            case "monday":
                return 1;
            case "tuesday":
                return 2;
            case "wednesday":
                return 3;
            case "thursday":
                return 4;
            case "friday":
                return 5;
            case "saturday":
                return 6;
            case "sunday":
                return 7;
        }
        throw new WrongDayNumException();
    }
    public static String numOfDayToNameOfDay(int dayNum) throws WrongDayNumException {
        switch (dayNum){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
        }
        throw new WrongDayNumException();
    }

    public static MyDigitalTime stringToTime (String rawTime) throws WrongTimeStringException {
        try {
            if (rawTime.length() != 5 || rawTime.charAt(2) != ':' || Integer.parseInt(rawTime.substring(0, 2)) > 23 ||
                    Integer.parseInt(rawTime.substring(3, 5)) > 60){
                throw new WrongTimeStringException();
            }
            return new MyDigitalTime(Integer.parseInt(rawTime.substring(0, 2)), Integer.parseInt(rawTime.substring(3, 5)));
        } catch (Exception exception){
            throw new WrongTimeStringException();
        }
    }

    public static MyDigitalTime getNowTime() throws WrongTimeCreated {
        LocalTime nowTime = LocalTime.now();
        return new MyDigitalTime(Integer.parseInt(nowTime.toString().substring(0, 2)),
                Integer.parseInt(nowTime.toString().substring(3, 5)));
    }

    public static String getCurrentDayOfWeek(){
        return LocalDate.now().getDayOfWeek().toString();
    }
}
