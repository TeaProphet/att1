package edu.csf.oop.java.schedule.console;

import edu.csf.oop.java.schedule.logic.*;
import edu.csf.oop.java.schedule.logic.exceptions.*;
import edu.csf.oop.java.schedule.logic.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupInterface {
    public static final String line = "==========================================================================";

    public static void start(GroupSchedule groupSchedule) {
            while (true) {
                try {
                System.out.println(line);
                System.out.println("""
                        Please select function of schedule by entering num of function:
                         1. Get weekly schedule
                         2. Get daily schedule
                         3. Get current subject
                         4. Add subject into schedule
                         5. Remove subject from schedule
                         6. Clear schedule
                         7. Clear daily schedule
                         8. Add student in a group
                         9. Remove student from a group
                         10. Print students list
                         11. Leave""");
                System.out.println(line);
                Scanner scanner = new Scanner(System.in);
                int chosen = scanner.nextInt();
                switch (chosen) {
                    case 1 -> {
                        System.out.println("Your weekly schedule: ");
                        System.out.println(groupSchedule.getWeeklySchedule());
                        System.out.println(line);
                    }

                    case 2 -> {
                        findDailySchedule(groupSchedule, scanner);
                        System.out.println(line);
                    }

                    case 3 -> {
                        if (groupSchedule.isNowLearning()) {
                            System.out.println(groupSchedule.getCurrentSubject());
                        } else {
                            System.out.println("Now is no subjects!");
                        }
                    }

                    case 4 -> addSubject(groupSchedule, scanner);

                    case 5 -> removeSubject(groupSchedule, scanner);

                    case 6 -> groupSchedule.clear();

                    case 7 -> {
                        System.out.print("Enter num of day of week (1-7): ");
                        int numOfDay = scanner.nextInt();
                        groupSchedule.getDailySchedule(numOfDay).clear();
                        System.out.println();
                    }

                    case 8 -> {
                        System.out.print("Enter full name with initials (example: Efremov V.A.): ");
                        String fullNameWithInitials = scanner.nextLine();
                        fullNameWithInitials = scanner.nextLine();
                        groupSchedule.addStudent(fullNameWithInitials);
                    }

                    case 9 -> {
                        System.out.print("Enter full name with initials (example: Efremov V.A.): ");
                        String fullNameWithInitials = scanner.nextLine();
                        fullNameWithInitials = scanner.nextLine();
                        groupSchedule.removeStudent(fullNameWithInitials);
                    }

                    case 10 -> {
                        ArrayList<Student> studentsList = groupSchedule.getStudents();
                        System.out.printf("List of students, course %d group %d:\n", groupSchedule.getCourseNumber(),
                                groupSchedule.getGroupNumber());
                        for (int i = 0; i < studentsList.size(); i++) {
                            System.out.printf("%d. %s\n", i+1, studentsList.get(i).getFullName());
                        }
                    }

                    case 11 -> {
                        return;
                    }

                    default -> System.out.println("ERROR: Your request is incorrect. Please try again.");
                }
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Please try again!");
            }
        }
    }

    private static void findDailySchedule(GroupSchedule schedule, Scanner scanner) {
        System.out.println("""
                Select day of week:
                 1-Monday
                 2-Tuesday
                 3-Wednesday
                 4-Thursday
                 5-Friday
                 6-Saturday
                 7-Sunday""");
        int dayOfWeek;
        String dailySchedule;
        while (true){
            try {
                dayOfWeek = scanner.nextInt();
                dailySchedule = TimeUtils.numOfDayToNameOfDay(dayOfWeek) + ":\n" + schedule.getDailySchedule(dayOfWeek).getStringSchedule();
                break;
            } catch (Exception e) {
                System.out.println("ERROR: Wrong value entered. Please try again");
            }
        }
        System.out.println(dailySchedule);
    }

    private static void addSubject(GroupSchedule schedule, Scanner scanner) throws WrongTimeStringException, WrongDayNumException, WrongStartEndException, WrongQueueOfSubjects {
        System.out.println("""
                Select day of week:
                                 1-Monday
                                 2-Tuesday
                                 3-Wednesday
                                 4-Thursday
                                 5-Friday
                                 6-Saturday
                                 7-Sunday""");
        int dayOfWeek = scanner.nextInt();
        System.out.print("\nEnter subject name: ");
        String subjectName = scanner.nextLine();
        subjectName = scanner.nextLine();
        System.out.print("\nEnter lecturer: ");
        String lecturer = scanner.nextLine();
        System.out.print("\nEnter auditory: ");
        String auditory = scanner.nextLine();
        System.out.print("\nEnter subject start time: ");
        String rawSubjectStart = scanner.nextLine();
        System.out.print("\nEnter subject end time: ");
        String rawSubjectEnd = scanner.nextLine();
        schedule.addSubject(dayOfWeek, lecturer, subjectName, auditory, rawSubjectStart, rawSubjectEnd);
    }

    private static void removeSubject(GroupSchedule schedule, Scanner scanner) throws WrongDayNumException, WrongSubjectNum {
        System.out.println("""
                Select day of week:
                                 1-Monday
                                 2-Tuesday
                                 3-Wednesday
                                 4-Thursday
                                 5-Friday
                                 6-Saturday
                                 7-Sunday""");
        int numOfDay = scanner.nextInt();
        System.out.print("\nEnter number of subject in daily schedule: ");
        int subjectNum = scanner.nextInt();
        schedule.removeSubject(numOfDay, subjectNum);
    }
}
