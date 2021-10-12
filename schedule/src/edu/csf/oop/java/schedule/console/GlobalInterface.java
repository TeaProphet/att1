package edu.csf.oop.java.schedule.console;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import edu.csf.oop.java.schedule.logic.GlobalScheduleHandler;

public class GlobalInterface {
    private static final String line = "==========================================================================";
    public static void main(String[] args) {
        GlobalScheduleHandler scheduleHandler = new GlobalScheduleHandler();
        while (true) {
            try {
                System.out.println(line);
                System.out.println("""
                        Please select function of schedule by entering num of function:
                         1. Add schedule for a course
                         2. Remove course schedule
                         3. Edit course schedule
                         4. Clear all schedules
                         5. Print all courses with number of groups and number of students
                         6. Exit""");
                System.out.println(line);
                Scanner scanner = new Scanner(System.in);
                int chosen = scanner.nextInt();
                switch (chosen) {

                    case 1 -> {
                        System.out.print("Enter course number: ");
                        int courseNum = scanner.nextInt();
                        System.out.print("\nEnter number of groups: ");
                        int groupsNum = scanner.nextInt();
                        scheduleHandler.createCourse(courseNum, groupsNum);
                    }

                    case 2 -> {
                        System.out.print("Enter course number: ");
                        int courseNum = scanner.nextInt();
                        scheduleHandler.removeCourse(courseNum);
                    }

                    case 3 -> {
                        System.out.print("Enter course number: ");
                        int courseNum = scanner.nextInt();
                        CourseInterface.start(scheduleHandler.getCourseSchedule(courseNum));
                    }

                    case 4 -> {
                        System.out.println("Are you sure about CLEARING ALL SCHEDULES? y/n");
                        String decision = scanner.nextLine();
                        decision = scanner.nextLine();
                        if (decision.equalsIgnoreCase("y")) {
                            scheduleHandler.clear();
                        }
                    }

                    case 5 -> {
                        HashMap<Integer, Integer> coursesWithNumberOfStudents = scheduleHandler.getNumberOfStudentsForEachCourse();
                        System.out.println("List of all courses with number of students: ");
                        for (int numberOfCourse: coursesWithNumberOfStudents.keySet()) {
                            System.out.printf("Course number %d, number of students: %d\n", numberOfCourse,
                                    coursesWithNumberOfStudents.get(numberOfCourse));
                        }
                    }

                    case 6 -> {
                        System.exit(1);
                    }

                    default -> {
                        System.out.println("ERROR: Your request is incorrect");
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("ERROR: Your request is incorrect");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

